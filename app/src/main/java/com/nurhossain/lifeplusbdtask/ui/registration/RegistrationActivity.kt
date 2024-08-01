package com.nurhossain.lifeplusbdtask.ui.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nurhossain.lifeplusbdtask.api.models.User
import com.nurhossain.lifeplusbdtask.databinding.ActivityRegistrationBinding
import com.nurhossain.lifeplusbdtask.ui.dashboard.DashboardActivity
import com.nurhossain.lifeplusbdtask.utils.Constants
import com.nurhossain.lifeplusbdtask.viemodel.UserViewModel

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var userViewModel: UserViewModel
    private var userName: String? = null
    //val viewModel : ContactViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        /*userViewModel.getUser().observe(this , Observer {  user->
            // set the layout manager and the adapter for the recycler view
            Toast.makeText(this, ""+user.last().name, Toast.LENGTH_SHORT).show()
        })*/

        userViewModel.saveStatus.observe(this, Observer { isSaved ->
            if (isSaved) {
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, DashboardActivity::class.java).putExtra(Constants.USERNAME,userName))
                finish()
            } else {
                Toast.makeText(this, "Failed to save", Toast.LENGTH_SHORT).show()
            }
        })


        binding.registration.setOnClickListener {
            val name = binding.name.text.toString()
            val username = binding.userName.text.toString()
            val phone = binding.userPhone.text.toString()
            val password = binding.password.text.toString()

            if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val data = User(name = name , username = username, phone = phone, password = password)

            userViewModel.addUser(data)
            userName = username
        }


    }
}