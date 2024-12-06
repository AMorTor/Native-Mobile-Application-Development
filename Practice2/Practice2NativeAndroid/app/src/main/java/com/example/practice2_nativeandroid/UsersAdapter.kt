package com.example.practice2_nativeandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practice2_nativeandroid.models.UserResponse

class UsersAdapter(private val onEditClick: (Int) -> Unit) :
    RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    var users = listOf<UserResponse>()

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val username: TextView = view.findViewById(R.id.usernameText)
        val lastname: TextView = view.findViewById(R.id.lastnameText)
        val email: TextView = view.findViewById(R.id.emailText)
        val role: TextView = view.findViewById(R.id.roleText)
        val editButton: TextView = view.findViewById(R.id.editButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.username.text = user.username
        holder.lastname.text = user.lastname
        holder.email.text = user.email
        holder.role.text = user.role
        holder.editButton.setOnClickListener { onEditClick(user.id) }
    }

    override fun getItemCount() = users.size
}
