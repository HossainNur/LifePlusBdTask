package com.nurhossain.lifeplusbdtask.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nurhossain.lifeplusbdtask.databinding.ActivityProfileBinding
import com.nurhossain.lifeplusbdtask.utils.Constants
import com.nurhossain.lifeplusbdtask.viemodel.UserViewModel


class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private val viewModel : UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(Constants.USERNAME)

        if (username != null){
            viewModel.getUserByUsername(username).observe(this, Observer { user ->
                user?.let {
                    val name = it.name
                    val userName = it.username
                    val phoneNumber = it.phone

                    if (name.isNotEmpty()) binding.tvNameValue.text = name
                    if (userName.isNotEmpty()) binding.tvUsernameValue.text = userName
                    if (phoneNumber.isNotEmpty()) binding.tvPhoneNumber.text = phoneNumber
                } ?: run {
                    // Handle the case when the user is not found
                    binding.tvNameValue.text = "User not found"
                }
            })
            viewModel.getUserByUsername(username)
        }


    }
}