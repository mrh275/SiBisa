package com.mrh.sibisa.ui.home.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mrh.sibisa.R
import com.mrh.sibisa.data.sign.SignItem
import com.mrh.sibisa.ui.home.MainAdapter
import com.mrh.sibisa.ui.home.MainViewModel

class HomeFragment(context: Context) : Fragment(){

    private lateinit var adapter: MainAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: MainViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = MainViewModel()
        val sharedPreferences = context?.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val layoutManager = LinearLayoutManager(context)
        val userNameHome = view.findViewById<TextView>(R.id.tv_user_name)
        userNameHome.text = sharedPreferences?.getString("NAME", "Pengguna")
        recyclerView = view.findViewById(R.id.rv_news)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        viewModel.setSigns()
        viewModel.getNews().observe(viewLifecycleOwner) {
            if (it != null) {
                adapter = MainAdapter(it)
                recyclerView.adapter = adapter
            }
        }
    }
}