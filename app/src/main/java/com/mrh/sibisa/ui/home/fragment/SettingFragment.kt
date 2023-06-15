package com.mrh.sibisa.ui.home.fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.mrh.sibisa.R
import com.mrh.sibisa.ui.customdialog.PopUpFragment

class SettingFragment(context: Context) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editName = view.findViewById<TextView>(R.id.settingChangeName)

        editName.setOnClickListener {
            val promptTitle = editName.text.toString()
            showDialogEdit(promptTitle)
        }
    }

    private fun showDialogEdit(promptTitle: String) {
        val showPopUp = PopUpFragment(promptTitle)
        showPopUp.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
    }
}