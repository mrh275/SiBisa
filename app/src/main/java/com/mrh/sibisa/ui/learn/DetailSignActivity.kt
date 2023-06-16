package com.mrh.sibisa.ui.learn

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.mrh.sibisa.BuildConfig
import com.mrh.sibisa.databinding.ActivityDetailSignBinding

class DetailSignActivity() : AppCompatActivity() {

    private lateinit var binding : ActivityDetailSignBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSignBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val signName = intent.getStringExtra("signName")
        Toast.makeText(this, signName, Toast.LENGTH_SHORT).show()

        val url = "https://sibisa.sman1rawamerta.sch.id/assets/img/signs/huruf/$signName.jpg"
        Glide.with(this)
            .load(url)
            .centerCrop()
            .into(binding.imgSignDetail)
        binding.signTitleDetail.text = signName
    }
}