package com.nurhossain.lifeplusbdtask.ui.dashboard

import android.content.Context
import android.content.Intent
import android.media.Image
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nurhossain.lifeplusbdtask.api.models.Data
import com.nurhossain.lifeplusbdtask.databinding.SearchResultLayoutBinding
import com.nurhossain.lifeplusbdtask.ui.details.DetailsviewActivityActivity
import com.nurhossain.lifeplusbdtask.utils.Constants
import com.squareup.picasso.Picasso

class SearchContentsAdapter(private val dataList: List<Data>?, private val context: Context) :
    RecyclerView.Adapter<SearchContentsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            SearchResultLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = dataList?.get(position)
        holder.binding.searchImage.clipToOutline = true
        val image = current?.thumbnailLandscape
        val title = current?.title

        image?.let { Picasso.get().load(it).into(holder.binding.searchImage) }
        title?.let { holder.binding.searchContentName.text = it }

        holder.binding.root.setOnClickListener {
            if (title != null && title.isNotEmpty()) {
                context.startActivity(
                    Intent(context, DetailsviewActivityActivity::class.java)
                        .putExtra(Constants.IMAGE, image)
                        .putExtra(Constants.TITLE, title)
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    fun clearListItem() {
        //dataList.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: SearchResultLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}
