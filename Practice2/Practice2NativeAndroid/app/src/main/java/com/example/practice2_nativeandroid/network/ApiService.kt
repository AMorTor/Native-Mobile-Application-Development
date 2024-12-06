package com.example.practice2_nativeandroid.network

import com.example.practice2_nativeandroid.models.LoginModel
import com.example.practice2_nativeandroid.models.SignupModel
import com.example.practice2_nativeandroid.models.AuthResponse
import com.example.practice2_nativeandroid.models.CreateModel
import com.example.practice2_nativeandroid.models.RoleResponse
import com.example.practice2_nativeandroid.models.UpdateModel
import com.example.practice2_nativeandroid.models.UserResponse
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService{
    @POST("/auth/login")
    suspend fun login(@Body loginModel: LoginModel): Response<AuthResponse>

    @POST("/auth/register")
    suspend fun signup(@Body signupModel: SignupModel): Response<AuthResponse>

    @GET("api/users/{id}")
    suspend fun role(@Path("id") userId: Int): Response<RoleResponse>

    @PUT("api/users/{id}")
    suspend fun updateUser(
        @Path("id") userId: Int,
        @Body userModel: UpdateModel
    ): Response<RoleResponse>

    @POST("api/users/")
    suspend fun createUser(@Body createModel: CreateModel): Response<AuthResponse>

    @DELETE("api/users/{id}")
    suspend fun deleteUser(@Path("id") userId: Int): Response<Void>

    @GET("api/users/")
    suspend fun getUsers(): Response<List<UserResponse>>



}