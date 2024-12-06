package com.example.practice2_nativeandroid.controllers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.practice2_nativeandroid.models.AuthResponse
import com.example.practice2_nativeandroid.models.LoginModel
import com.example.practice2_nativeandroid.network.ApiService

class LoginController(private val apiService: ApiService) {
    private val _loginResult = MutableLiveData<Result<AuthResponse>>()
    val loginResult: LiveData<Result<AuthResponse>> = _loginResult

    suspend fun login(username: String, password: String) {
        try {
            val loginData = LoginModel(username = username, password = password)
            val response = apiService.login(loginData)
            if (response.isSuccessful) {
                response.body()?.let {
                    _loginResult.postValue(Result.success(it))
                }
            } else {
                _loginResult.postValue(Result.failure(Exception("Error en el inicio de sesión")))
            }
        } catch (e: Exception) {
            _loginResult.postValue(Result.failure(e))
        }
    }
}
