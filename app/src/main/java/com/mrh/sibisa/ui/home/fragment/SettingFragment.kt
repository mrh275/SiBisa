package com.mrh.sibisa.ui.home.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.mrh.sibisa.R
import com.mrh.sibisa.ui.about.AboutActivity
import com.mrh.sibisa.ui.auth.login.LoginActivity
import com.mrh.sibisa.ui.customdialog.PopUpFragment

class SettingFragment(context: Context, token: String) : Fragment() {

    private val token = token
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
        val sharedPreferences = context?.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        checkAuth(sharedPreferences)
        super.onViewCreated(view, savedInstanceState)

        val editName = view.findViewById<TextView>(R.id.settingChangeName)
        val editEmail = view.findViewById<TextView>(R.id.settingChangeEmail)
        val editPassword = view.findViewById<TextView>(R.id.settingChangePassword)
        val aboutUs = view.findViewById<TextView>(R.id.settingAbout)

        editName.setOnClickListener {
            val promptTitle = editName.text.toString()
            showDialogEdit(promptTitle, 1, token)
        }

        editEmail.setOnClickListener {
            val promptTitle = editEmail.text.toString()
            showDialogEdit(promptTitle, 2, token)
        }

        editPassword.setOnClickListener {
            val promptTitle = editPassword.text.toString()
            showDialogEdit(promptTitle, 3, token)
        }

        aboutUs.setOnClickListener {
            activity?.let {
                it.startActivity(Intent(it, AboutActivity::class.java))
            }
        }
    }

    private fun checkAuth(sharedPreferences: SharedPreferences?) {
        if(sharedPreferences?.getString("AUTH_TOKEN", null) == null) {
            activity?.let {
                it.startActivity(Intent(it, LoginActivity::class.java))
                it.finishAfterTransition()
            }
        }
    }

    private fun showDialogEdit(promptTitle: String, idDialog: Int, token: String) {
        val showPopUp = PopUpFragment(promptTitle, idDialog, token)
        showPopUp.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
    }
}