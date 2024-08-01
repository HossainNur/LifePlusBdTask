package com.nurhossain.lifeplusbdtask.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nurhossain.lifeplusbdtask.R
import android.content.Intent
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nurhossain.lifeplusbdtask.databinding.ActivityLoginBinding
import com.nurhossain.lifeplusbdtask.ui.registration.RegistrationActivity
import com.nurhossain.lifeplusbdtask.viemodel.UserViewModel


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.login.setOnClickListener {
            val username = binding.userName.text.toString()
            val password = binding.password.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "All fields must be required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            loginUser(username,password)

        }
        binding.registration.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegistrationActivity::class.java))
        }

        userViewModel.user.observe(this, Observer { user ->
            if (user != null) {
                // User is valid, navigate to the next screen or update UI
                Toast.makeText(this, "Successfully Login", Toast.LENGTH_SHORT).show()
            } else {
                // Show error message
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun loginUser(username: String, password: String) {
        userViewModel.getValidUser(username,password)
    }



    /*private fun loginUser(username: String, password: String) {
        userViewModel.getUser(username, password).observe(this, Observer { user ->
            if (user != null) {
                Toast.makeText(this, "Successfully Login", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, DashboardActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        })
    }*/
}