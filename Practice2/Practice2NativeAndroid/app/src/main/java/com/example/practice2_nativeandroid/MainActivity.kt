package com.example.practice2_nativeandroid

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.auth0.android.jwt.JWT
import androidx.core.content.edit
import com.example.practice2_nativeandroid.controllers.LoginController
import com.example.practice2_nativeandroid.network.ApiService
import com.example.practice2_nativeandroid.network.RetrofitClient
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.practice2_nativeandroid.controllers.RoleController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button
    private lateinit var createAccountLink: TextView
    private lateinit var errorText: TextView
    private lateinit var controller: LoginController
    private lateinit var roleController: RoleController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setupControllers()
        setupClickListeners()
        observeLoginResult()
        setupRoleObserver()
    }

    private fun initViews() {
        usernameInput = findViewById(R.id.usernameInput)
        passwordInput = findViewById(R.id.passwordInput)
        loginButton = findViewById(R.id.loginButton)
        createAccountLink = findViewById(R.id.createAccountLink)
        errorText = findViewById(R.id.errorText)
    }

    private fun setupControllers() {
        val retrofit = RetrofitClient.getInstance()
        val apiService = retrofit.create(ApiService::class.java)
        controller = LoginController(apiService)
        roleController = RoleController(apiService)
    }

    private fun setupClickListeners() {
        loginButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            if (validateInput(username, password)) {
                CoroutineScope(Dispatchers.IO).launch {
                    loginButton.isEnabled = false
                    errorText.visibility = View.GONE
                    controller.login(username, password)
                }
            }
        }

        createAccountLink.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }

    private fun validateInput(username: String, password: String): Boolean {
        if (username.isEmpty() || password.isEmpty()) {
            errorText.apply {
                text = "Por favor completa todos los campos"
                visibility = View.VISIBLE
            }
            return false
        }
        return true
    }

    private fun setupRoleObserver() {
        roleController.roleResult.observe(this) { result ->
            result.fold(
                onSuccess = { userInfo ->
                    getSharedPreferences("auth", MODE_PRIVATE).edit {
                        putString("username", userInfo.username)
                        putString("email", userInfo.email)
                        putString("lastname", userInfo.lastname)
                        putString("role", userInfo.role)
                        apply()
                    }

                    if (userInfo.role.uppercase() == "ADMIN") {
                        startActivity(Intent(this, ProfileActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        })
                    } else {
                        startActivity(Intent(this, UserActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        })
                    }

                    finish()
                },
                onFailure = { exception ->
                    errorText.apply {
                        text = exception.message ?: "Error al obtener rol"
                        visibility = View.VISIBLE
                    }
                }
            )
        }
    }

    private fun observeLoginResult() {
        controller.loginResult.observe(this) { result ->
            loginButton.isEnabled = true
            result.fold(
                onSuccess = { authResponse ->
                    CoroutineScope(Dispatchers.IO).launch {
                        handleSuccessfulLogin(authResponse.token)
                    }
                },
                onFailure = { exception ->
                    errorText.apply {
                        text = exception.message
                        visibility = View.VISIBLE
                    }
                }
            )
        }
    }

    private suspend fun handleSuccessfulLogin(token: String) {
        withContext(Dispatchers.IO) {
            try {
                val jwt = JWT(token)
                val userId = jwt.getClaim("userId").asInt() ?: throw Exception("UserId no encontrado")

                withContext(Dispatchers.Main) {
                    getSharedPreferences("auth", MODE_PRIVATE).edit {
                        putString("token", token)
                        putInt("userId", userId)
                        apply()
                    }
                }

                roleController.getRole(userId)

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    errorText.apply {
                        text = "Error al procesar la sesión"
                        visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}