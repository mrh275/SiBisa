package com.mrh.sibisa.ui.learn

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mrh.sibisa.data.sign.LettersDataItem
import com.mrh.sibisa.databinding.ListLearnBinding
import kotlinx.coroutines.withContext

class LearnAdapter(private var listLetter: List<LettersDataItem?>?) : RecyclerView.Adapter<LearnAdapter.MyViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class MyViewHolder(private val binding: ListLearnBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(letter: LettersDataItem) {
            binding.tvSignCategory.text = letter.category
            binding.tvSignTitle.text = letter.sign
            val url = "https://sibisa.sman1rawamerta.sch.id/assets/img/signs/huruf/${letter.sign}.jpg"
            Glide.with(itemView)
                .load(url)
                .centerCrop()
                .into(binding.ivListImage)
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(letter)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ListLearnBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listLetter!!.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        listLetter?.get(position)?.let { holder.bind(it) }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: LettersDataItem)
    }
}
