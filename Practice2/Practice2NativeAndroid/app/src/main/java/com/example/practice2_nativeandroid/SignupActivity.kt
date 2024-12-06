package com.example.practice2_nativeandroid

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.lifecycle.lifecycleScope
import com.example.practice2_nativeandroid.controllers.SignupController
import com.example.practice2_nativeandroid.databinding.ActivitySignupBinding
import com.example.practice2_nativeandroid.network.ApiService
import com.example.practice2_nativeandroid.network.RetrofitClient
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignupActivity : ComponentActivity() {
    private lateinit var emailInput: EditText
    private lateinit var usernameInput: EditText
    private lateinit var lastnameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var signupButton: Button
    private lateinit var loginLink: TextView
    private lateinit var errorText: TextView
    private lateinit var controller: SignupController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        initViews()
        setupController()
        setupClickListeners()
        observeSignupResult()
    }

    private fun initViews() {
        emailInput = findViewById(R.id.emailInput)
        usernameInput = findViewById(R.id.usernameInput)
        lastnameInput = findViewById(R.id.lastnameInput)
        passwordInput = findViewById(R.id.passwordInput)
        signupButton = findViewById(R.id.signupButton)
        loginLink = findViewById(R.id.loginLink)
        errorText = findViewById(R.id.errorText)
    }

    private fun setupController() {
        val retrofit = RetrofitClient.getInstance()
        val apiService = retrofit.create(ApiService::class.java)
        controller = SignupController(apiService)
    }

    private fun setupClickListeners() {
        signupButton.setOnClickListener {
            val email = emailInput.text.toString()
            val username = usernameInput.text.toString()
            val lastname = lastnameInput.text.toString()
            val password = passwordInput.text.toString()

            if (validateInput(email, username, lastname, password)) {
                lifecycleScope.launch {
                    signupButton.isEnabled = false
                    errorText.visibility = View.GONE
                    controller.signup(email, username, lastname, password)
                }
            }
        }

        loginLink.setOnClickListener {
            finish()
        }
    }

    private fun validateInput(email: String, username: String, lastname: String, password: String): Boolean {
        if (email.isEmpty() || username.isEmpty() || lastname.isEmpty() || password.isEmpty()) {
            errorText.apply {
                text = "Por favor completa todos los campos"
                visibility = View.VISIBLE
            }
            return false
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errorText.apply {
                text = "Por favor ingresa un correo válido"
                visibility = View.VISIBLE
            }
            return false
        }
        return true
    }

    private fun observeSignupResult() {
        controller.signupResult.observe(this) { result ->
            signupButton.isEnabled = true
            result.fold(
                onSuccess = { authResponse ->
                    // Guardar token
                    getSharedPreferences("auth", MODE_PRIVATE).edit {
                        putString("token", authResponse.token)
                        apply()
                    }
                    // Ir a ProfileActivity
                    startActivity(Intent(this, ProfileActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    })
                    finish()
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
}