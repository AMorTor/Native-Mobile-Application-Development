package com.example.practice2_nativeandroid.controllers

sealed class LoginState {
    data class Success(val token: String) : LoginState()
    data class Error(val message: String) : LoginState()
}