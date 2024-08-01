package com.nurhossain.lifeplusbdtask.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.nurhossain.lifeplusbdtask.R
import com.nurhossain.lifeplusbdtask.databinding.ActivityDetailsviewActivityBinding
import com.nurhossain.lifeplusbdtask.utils.Constants
import com.squareup.picasso.Picasso

class DetailsviewActivityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsviewActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsviewActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val image = intent.getStringExtra(Constants.IMAGE)
        val title = intent.getStringExtra(Constants.TITLE)

        if (title != null) binding.title.text = title
        if (image != null) Picasso.get().load(image).into(binding.image)
    }
}