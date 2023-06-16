package com.mrh.sibisa.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mrh.sibisa.data.news.NewsDataItem
import com.mrh.sibisa.databinding.ListItemBinding

class MainAdapter(private var listNews: List<NewsDataItem>) : RecyclerView.Adapter<MainAdapter.MyViewHolder>() {
    class MyViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val newsTitle = binding.tvNewsTitle
        val newsExcerpt = binding.tvNewsExcerpt
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listNews.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val newsList = listNews[position]
        holder.newsTitle.text = newsList.newsTitle
        holder.newsExcerpt.text = newsList.newsExcerpt
    }
}