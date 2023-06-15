package com.mrh.sibisa.ui.auth.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.mrh.sibisa.databinding.ActivityLoginBinding
import com.mrh.sibisa.ui.auth.AuthViewModel
import com.mrh.sibisa.ui.auth.register.RegisterActivity
import com.mrh.sibisa.ui.home.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: AuthViewModel
    private var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = AuthViewModel()

        binding.tvSignup.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            showLoading(true)
            val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
            val editorPref = sharedPreferences.edit()
            try {
                val email = binding.fieldEmail.text.toString()
                val password = binding.fieldPassword.text.toString()

                viewModel.login(email, password)
                viewModel.getAuthData().observe(this) {
                    editorPref.apply {
                        putString("AUTH_TOKEN", it?.accessToken)
                        putString("NAME", it?.userData?.name)
                        putString("EMAIL", it?.userData?.email)
                    }.apply()

                    startActivity(Intent(this, MainActivity::class.java))
                    finishAfterTransition()
                    showLoading(false)
                }
            } catch (e: Exception) {
                Log.e("LoginActivity", e.message.toString())
                Toast.makeText(this, "Login gagal! Kendala server", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}