package com.example.practice2_nativeandroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practice2_nativeandroid.network.ApiService
import com.example.practice2_nativeandroid.network.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileActivity : ComponentActivity() {
    private lateinit var usersAdapter: UsersAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard)
        setupViews()
        loadUsers()
    }

    private fun setupViews() {
        recyclerView = findViewById(R.id.usersRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        usersAdapter = UsersAdapter { userId ->
            startActivity(Intent(this, UserActivity::class.java).apply {
                putExtra("userId", userId)
            })
        }
        recyclerView.adapter = usersAdapter
    }

    private fun loadUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitClient.getInstance()
                    .create(ApiService::class.java)
                    .getUsers()

                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                        response.body()?.let { users ->
                            usersAdapter.users = users
                            usersAdapter.notifyDataSetChanged()
                        }
                    }
                }
            } catch (e: Exception) {
                // Manejar error
            }
        }
    }
}