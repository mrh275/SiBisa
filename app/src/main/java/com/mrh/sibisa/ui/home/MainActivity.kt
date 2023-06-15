package com.mrh.sibisa.ui.home

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.mrh.sibisa.ui.home.fragment.HomeFragment
import com.mrh.sibisa.R
import com.mrh.sibisa.databinding.ActivityMainBinding
import com.mrh.sibisa.ui.auth.AuthViewModel
import com.mrh.sibisa.ui.auth.login.LoginActivity
import com.mrh.sibisa.ui.home.fragment.ProfileFragment
import com.mrh.sibisa.ui.home.fragment.SettingFragment
import com.mrh.sibisa.ui.translate.CameraActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var authViewModel: AuthViewModel
    private var backPressedTime: Long = 0

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == REQUEST_CODE_PERMISSIONS) {
            if(!allPermissionsGranted()) {
                Toast.makeText(
                    this,
                    "Tidak mendapatkan permission.",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        authViewModel = AuthViewModel()
        binding.bottomNavigation.background = null
        binding.bottomNavigation.menu.getItem(2).isEnabled = false
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editorPref = sharedPreferences.edit()
        val token = sharedPreferences.getString("AUTH_TOKEN", null)
        Toast.makeText(this,token.toString(), Toast.LENGTH_SHORT).show()

        val homeFragment = HomeFragment()
        val profileFragment = ProfileFragment()
        val settingFragment = SettingFragment()

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }

        replaceFragment(homeFragment)
        binding.fabTranslate.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }

        binding.bottomNavigation.setOnItemSelectedListener { it ->
            when(it.itemId) {
                R.id.nav_home -> replaceFragment(homeFragment)
                R.id.nav_profile -> replaceFragment(profileFragment)
                R.id.nav_setting -> replaceFragment(settingFragment)
                R.id.nav_login -> {
                    if(token != null) {
                        showLoading(true)
                        authViewModel.logout(token)
                        authViewModel.getLogoutResponse().observe(this) { response ->
                            editorPref.apply {
                                putString("AUTH_TOKEN", null)
                            }.apply()
                            Toast.makeText(this, response?.message, Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                            showLoading(false)
                        }
                    } else {
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }
                }
            }
            true
        }
    }

    override fun onBackPressed() {
        if(backPressedTime + 3000 > System.currentTimeMillis()) {
            super.onBackPressed()
            finish()
        } else {
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()
        }
        backPressedTime = System.currentTimeMillis()
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.slide_in_rtl, R.anim.fade_out)
            .replace(R.id.frame_container, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }
    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        const val CAMERA_X_RESULT = 200

        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }
}