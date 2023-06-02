package com.mrh.sibisa.ui.auth.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mrh.sibisa.data.register.Users
import com.mrh.sibisa.databinding.ActivityRegisterBinding
import com.mrh.sibisa.ui.auth.login.LoginActivity
import com.mrh.sibisa.ui.home.MainActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        firebaseAuth = FirebaseAuth.getInstance()
        setContentView(binding.root)
        dbRef = FirebaseDatabase.getInstance().getReference("users")

        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.btnRegister.setOnClickListener {
            try {
                val name = binding.fieldName.text.toString()
                val email = binding.fieldEmail.text.toString()
                val password = binding.fieldPassword.text.toString()
                if(name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                        if(it.isSuccessful) {
                            val userId = dbRef.push().key!!
                            val user = Users(name, email)
                            dbRef.child(userId).setValue(user)
                                .addOnCompleteListener { response ->
                                    if(response.isSuccessful) {
                                        Toast.makeText(this, "Registrasi berhasil! Silahkan login.",Toast.LENGTH_SHORT).show()
                                        startActivity(Intent(this, LoginActivity::class.java))
                                        finish()
                                    } else {
                                        Toast.makeText(this, "Gagal menyimpan data!",Toast.LENGTH_SHORT).show()
                                        Log.e("RegisterActivity", response.exception.toString())
                                    }
                                }
                        } else {
                            Toast.makeText(this, "Gagal melakukan pendaftaran!", Toast.LENGTH_SHORT).show()
                            Log.e("RegisterActivity", it.exception.toString())
                        }
                    }
                } else {
                    Toast.makeText(this, "Kolom tidak boleh kosong!", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.e("Register Activity", e.message.toString())
                Toast.makeText(this, "Registrasi gagal! Kendala server", Toast.LENGTH_SHORT).show()
            }
        }
    }
}