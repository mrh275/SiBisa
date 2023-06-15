package com.mrh.sibisa.ui.home.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mrh.sibisa.R

class ProfileFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences = context?.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)

        val profileName = view.findViewById<TextView>(R.id.tv_profile_name)
        val profileEmail = view.findViewById<TextView>(R.id.tv_profile_email)
        profileName.text = sharedPreferences?.getString("NAME", "Nama Pengguna")
        profileEmail.text = sharedPreferences?.getString("EMAIL", "email@email.com")
    }
}