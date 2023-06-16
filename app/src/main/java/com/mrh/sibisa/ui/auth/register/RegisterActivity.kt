package com.mrh.sibisa.ui.auth.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.mrh.sibisa.databinding.ActivityRegisterBinding
import com.mrh.sibisa.ui.auth.AuthViewModel
import com.mrh.sibisa.ui.auth.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = AuthViewModel()

        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.btnRegister.setOnClickListener {
            showLoading(true)
            try {
                val name = binding.fieldName.text.toString()
                val email = binding.fieldEmail.text.toString()
                val password = binding.fieldPassword.text.toString()

                viewModel.register(name, email, password)
                viewModel.getRegisterData().observe(this) {
                    Toast.makeText(this, "Registrasi berhasil!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finishAfterTransition()
                    showLoading(false)
                }
            } catch (e: Exception) {
                Log.e("Register Activity", e.message.toString())
                Toast.makeText(this, "Registrasi gagal! Kendala server", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, LoginActivity::class.java))
        finishAfterTransition()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}