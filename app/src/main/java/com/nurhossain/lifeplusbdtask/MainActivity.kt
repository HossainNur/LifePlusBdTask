package com.nurhossain.lifeplusbdtask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.nurhossain.lifeplusbdtask.viemodel.UserViewModel

class MainActivity : AppCompatActivity() {
    val viewModel : UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val data = User(name = "nur" , username = "nur1",phone = "8563",password = "123")

        viewModel.addUser(data)*/

        viewModel.getValidUser("nur1", "123")

        /*viewModel.saveStatus.observe(this, Observer { isSaved ->
            if (isSaved) {
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
                //startActivity(Intent(this, DashboardActivity::class.java))
            } else {
                Toast.makeText(this, "Failed to save", Toast.LENGTH_SHORT).show()
            }
        })*/


        /*viewModel.getUser("nur1", "123").observe(this, Observer { user ->
            if (user != null) {
                Toast.makeText(this, "Successfully Login", Toast.LENGTH_SHORT).show()
                //startActivity(Intent(this, DashboardActivity::class.java))
                //finish()
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        })*/

        viewModel.user.observe(this, Observer { user ->
            if (user != null) {
                // User is valid, navigate to the next screen or update UI
                Toast.makeText(this, "Successfully Login", Toast.LENGTH_SHORT).show()
            } else {
                // Show error message
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        })
    }
}