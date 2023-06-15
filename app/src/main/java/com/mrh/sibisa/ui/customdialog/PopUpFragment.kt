package com.mrh.sibisa.ui.customdialog

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.mrh.sibisa.R


class PopUpFragment(promptTitle: String) : DialogFragment() {

    private val promptTitle = promptTitle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pop_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences = context?.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)

        val btnYes = view.findViewById<Button>(R.id.btnDialogYes)
        val btnNo = view.findViewById<Button>(R.id.btnDialogNo)
        val promptTitleText = view.findViewById<TextView>(R.id.tv_prompt_title)
        val editText = view.findViewById<EditText>(R.id.edit_prompt_text)

        promptTitleText.text = promptTitle
        editText.setText(sharedPreferences?.getString("NAME", null))

        btnNo.setOnClickListener {
            dismiss()
        }

        btnYes.setOnClickListener {
            dismiss()
        }
    }
}