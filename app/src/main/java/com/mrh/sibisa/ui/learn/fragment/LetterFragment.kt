package com.mrh.sibisa.ui.learn.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mrh.sibisa.R
import com.mrh.sibisa.data.sign.LettersDataItem
import com.mrh.sibisa.ui.learn.DetailSignActivity
import com.mrh.sibisa.ui.learn.LearnAdapter
import com.mrh.sibisa.ui.learn.LearnViewModel

class LetterFragment(private val token: String) : Fragment() {

    private lateinit var adapter: LearnAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: LearnViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_letter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = LearnViewModel()
        val layoutManager = LinearLayoutManager(context)

        recyclerView = view.findViewById(R.id.rv_signs)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        viewModel.setLettersSign(token)
        viewModel.getLetterSign().observe(viewLifecycleOwner) {
            if(it != null) {
                adapter = LearnAdapter(it)
                recyclerView.adapter = adapter
                adapter.setOnItemClickCallback(object : LearnAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: LettersDataItem) {
                        activity?.let {
//                            Toast.makeText(requireContext(), data.sign, Toast.LENGTH_SHORT).show()
                            val detail = Intent(it, DetailSignActivity::class.java).also { detail ->
                                detail.putExtra("signName", data.sign)
                            }
                            startActivity(detail)
                        }
                    }
                })
            }
        }
    }
}