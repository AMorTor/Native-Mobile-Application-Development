package com.example.practice2_nativeandroid.controllers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.practice2_nativeandroid.models.SignupModel
import com.example.practice2_nativeandroid.models.AuthResponse
import com.example.practice2_nativeandroid.network.ApiService

class SignupController(private val apiService: ApiService) {
    private val _signupResult = MutableLiveData<Result<AuthResponse>>()
    val signupResult: LiveData<Result<AuthResponse>> = _signupResult

    suspend fun signup(email: String, username: String, lastname: String, password: String) {
        try {
            val signupData = SignupModel(email, username, lastname, password)
            val response = apiService.signup(signupData)
            if (response.isSuccessful) {
                response.body()?.let {
                    _signupResult.postValue(Result.success(it))
                }
            } else {
                _signupResult.postValue(Result.failure(Exception("Error al crear la cuenta")))
            }
        } catch (e: Exception) {
            _signupResult.postValue(Result.failure(e))
        }
    }
}