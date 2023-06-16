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
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.mrh.sibisa.R
import com.mrh.sibisa.ui.home.MainViewModel


class PopUpFragment(promptTitle: String, idDialog: Int, token: String) : DialogFragment() {

    private val promptTitle = promptTitle
    private val idDialog = idDialog
    private val token = token
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pop_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferences = requireContext().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        viewModel = MainViewModel()

        val btnYes = view.findViewById<Button>(R.id.btnDialogYes)
        val btnNo = view.findViewById<Button>(R.id.btnDialogNo)
        val promptTitleText = view.findViewById<TextView>(R.id.tv_prompt_title)
        val editText = view.findViewById<EditText>(R.id.edit_prompt_text)


        promptTitleText.text = promptTitle

        if(idDialog == 1) {
            val oldValue = sharedPreferences?.getString("NAME", null)
            editText.setText(oldValue)
        } else if(idDialog == 2) {
            val oldValue = sharedPreferences?.getString("EMAIL", null)
            editText.setText(oldValue)
        } else if(idDialog == 3) {
            val oldValue = sharedPreferences?.getString("PASSWORD", null)
            editText.setText(oldValue)
        }

        btnNo.setOnClickListener {
            dismiss()
        }

        btnYes.setOnClickListener {
            val editor = sharedPreferences.edit()
            var newName = sharedPreferences?.getString("NAME", null)
            var newEmail = sharedPreferences?.getString("EMAIL", null)
            var newPassword = ""
            when (idDialog) {
                1 -> {
                    newName = editText.text.toString()
                    editor.apply{
                        putString("NAME", newName)
                    }.apply()
                }
                2 -> {
                    newEmail = editText.text.toString()
                    editor.apply{
                        putString("EMAIL", newEmail)
                    }.apply()
                }
                3 -> {
                    newPassword = editText.text.toString()
                }
            }
            viewModel.updateUser(newName, newEmail, newPassword, token)
            viewModel.getUserUpdateResponse().observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), it?.message, Toast.LENGTH_SHORT).show()
                dismiss()
            }
        }
    }
}