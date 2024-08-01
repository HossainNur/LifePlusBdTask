package com.nurhossain.lifeplusbdtask.ui.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import com.nurhossain.lifeplusbdtask.databinding.ActivityDashboardBinding
import com.nurhossain.lifeplusbdtask.ui.profile.ProfileActivity

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    //private val viewModel : SearchViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.profile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        /*binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (!query.isNullOrEmpty()) viewModel.getSearch(query.trim())
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        viewModel.searchResponse.observe(this){searchResult ->
            Toast.makeText(this,"nur"+searchResult.name,Toast.LENGTH_SHORT).show()
        }*/
    }
}