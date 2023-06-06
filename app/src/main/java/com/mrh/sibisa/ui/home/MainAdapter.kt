package com.mrh.sibisa.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mrh.sibisa.data.sign.SignItem
import com.mrh.sibisa.databinding.ListItemBinding

class MainAdapter(private var listSign: List<SignItem?>?) : RecyclerView.Adapter<MainAdapter.MyViewHolder>() {
    class MyViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val signText = binding.tvSign
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listSign?.count()!!
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val signList = listSign?.get(position)
        if (signList != null) {
            holder.signText.text = signList.letter.toString()
        }
    }
}