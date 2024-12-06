package com.example.practice2_nativeandroid
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AlertDialog
import com.example.practice2_nativeandroid.controllers.RoleController
import com.example.practice2_nativeandroid.models.UpdateModel
import com.example.practice2_nativeandroid.network.ApiService
import com.example.practice2_nativeandroid.network.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class UserActivity : ComponentActivity() {
    private lateinit var usernameInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var lastnameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var saveButton: Button
    private lateinit var deleteProfileButton: Button
    private lateinit var logoutText: TextView
    private lateinit var errorText: TextView
    private lateinit var roleController: RoleController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        initViews()
        setupUserData()
        setupClickListeners()
    }

    private fun initViews() {
        usernameInput = findViewById(R.id.usernameInput)
        emailInput = findViewById(R.id.emailInput)
        lastnameInput = findViewById(R.id.lastnameInput)
        passwordInput = findViewById(R.id.passwordInput)
        saveButton = findViewById(R.id.saveButton)
        deleteProfileButton = findViewById(R.id.deleteProfileButton)
        logoutText = findViewById(R.id.logoutText)
    }

    private fun setupUserData() {
        val sharedPrefs = getSharedPreferences("auth", MODE_PRIVATE)
        usernameInput.setText(sharedPrefs.getString("username", ""))
        emailInput.setText(sharedPrefs.getString("email", ""))
        lastnameInput.setText(sharedPrefs.getString("lastname", ""))
    }

    private fun setupClickListeners() {
        saveButton.setOnClickListener {
            updateProfile()
        }

        deleteProfileButton.setOnClickListener {
            showDeleteConfirmationDialog()
        }

        logoutText.setOnClickListener {
            getSharedPreferences("auth", MODE_PRIVATE).edit().clear().apply()
            startActivity(Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            })
            finish()
        }
    }

    private fun showDeleteConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle("¿Estás seguro de que quieres eliminar tu perfil?")
            .setMessage("Esta acción no se puede deshacer. Todos tus datos serán eliminados permanentemente.")
            .setPositiveButton("Si, eliminar perfil", null)
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun updateProfile() {
        validateAndUpdate()
    }

    private fun deleteProfile() {
        // Implementar eliminación de perfil
    }

    private fun validateAndUpdate() {
        val sharedPrefs = getSharedPreferences("auth", MODE_PRIVATE)
        val userid = sharedPrefs.getInt("userId", 0)
        val username = usernameInput.text.toString()
        val email = emailInput.text.toString()
        val lastname = lastnameInput.text.toString()
        val password = passwordInput.text.toString()

        if (username.isEmpty() || email.isEmpty() || lastname.isEmpty() || password.isEmpty()) {
            showError("Todos los campos son obligatorios")
            return
        }

        if (!email.matches(Regex("^[A-Za-z0-9+_.-]+@(.+)\\.com$"))) {
            showError("Email inválido")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val updateModel = UpdateModel(username, email, lastname, password)
                val response = RetrofitClient.getInstance()
                    .create(ApiService::class.java)
                    .updateUser(userid, updateModel)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        finish()
                    } else {
                        showError("Error al actualizar")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    showError(e.message ?: "Error al actualizar")
                }
            }
        }
    }

    private fun showError(message: String) {
        errorText.text = message
        errorText.visibility = View.VISIBLE
    }

}