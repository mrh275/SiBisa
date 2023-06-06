package com.mrh.sibisa.ui.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mrh.sibisa.R
import com.mrh.sibisa.data.sign.SignItem
import com.mrh.sibisa.ui.home.MainAdapter
import com.mrh.sibisa.ui.home.MainViewModel

class HomeFragment : Fragment(){

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
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.rv_signs)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        viewModel.setSigns()
        viewModel.getSigns().observe(viewLifecycleOwner) {
            if (it != null) {
                adapter = MainAdapter(it)
                recyclerView.adapter = adapter
            }
        }
    }
}