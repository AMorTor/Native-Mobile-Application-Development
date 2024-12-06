package com.example.practice2_nativeandroid.controllers

import androidx.compose.ui.semantics.Role
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.practice2_nativeandroid.models.RoleResponse
import com.example.practice2_nativeandroid.network.ApiService

class RoleController(private val apiService: ApiService) {
    private val _roleResult = MutableLiveData<Result<RoleResponse>>()
    val roleResult: LiveData<Result<RoleResponse>> = _roleResult

    suspend fun getRole(userId: Int) {
        try {
            val response = apiService.role(userId)
            if (response.isSuccessful) {
                response.body()?.let {
                    _roleResult.postValue(Result.success(it))
                }
            } else {
                _roleResult.postValue(Result.failure(Exception("Error al obtener rol")))
            }
        } catch (e: Exception) {
            _roleResult.postValue(Result.failure(e))
        }
    }
}