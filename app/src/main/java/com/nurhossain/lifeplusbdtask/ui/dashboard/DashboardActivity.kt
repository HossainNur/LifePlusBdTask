package com.nurhossain.lifeplusbdtask.ui.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nurhossain.lifeplusbdtask.databinding.ActivityDashboardBinding
import com.nurhossain.lifeplusbdtask.ui.profile.ProfileActivity
import com.nurhossain.lifeplusbdtask.utils.Constants
import com.nurhossain.lifeplusbdtask.viemodel.SearchViewModel

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private val viewModel : SearchViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userName = intent.getStringExtra(Constants.USERNAME)

        binding.profile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java).putExtra(Constants.USERNAME,userName))
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (!query.isNullOrEmpty()) {
                    viewModel.getSearchContents(query.trim())
                    binding.progressBar.visibility = View.VISIBLE
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (!newText.isNullOrEmpty()) {
                    binding.progressBar.visibility = View.VISIBLE
                    viewModel.getSearchContents(newText.trim())
                }
                return false
            }
        })

        viewModel.searchContent.observe(this){searchResult ->
            if (searchResult != null){
                binding.searchResultRv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
                binding.searchResultRv.adapter = SearchContentsAdapter(searchResult.data, this)
                binding.progressBar.visibility = View.GONE
                binding.searchResultRv.visibility = View.VISIBLE
            }else{
                binding.progressBar.visibility = View.GONE
                binding.searchResultRv.visibility = View.GONE
            }
        }
    }
}