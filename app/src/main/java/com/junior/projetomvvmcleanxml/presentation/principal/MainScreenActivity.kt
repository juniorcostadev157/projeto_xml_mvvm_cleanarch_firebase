package com.junior.projetomvvmcleanxml.presentation.principal

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.junior.projetomvvmcleanxml.R
import com.junior.projetomvvmcleanxml.databinding.ActivityMainScreenBinding
import com.junior.projetomvvmcleanxml.presentation.login.LoginActivity
import com.junior.projetomvvmcleanxml.presentation.login.SessionViewModel
import com.junior.projetomvvmcleanxml.presentation.utils.InjectContainer

class MainScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainScreenBinding
    private lateinit var sessionViewModel: SessionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main_screen)

         AppBarConfiguration(
            setOf(
                R.id.navigation_list_item_cloud, R.id.navigation_create_item, R.id.navigation_list_item_local
            )
        )

        sessionViewModel = ViewModelProvider(this, InjectContainer.sessionFactory)[SessionViewModel::class.java]
        sessionViewModel.isLoggedIn.observe(this){isLogged->
            if (!isLogged){
               navigateToLogin()
            }

        }
        navView.setupWithNavController(navController)
        requestNotificationPermission()
    }

    override fun onStart() {
        super.onStart()
        sessionViewModel.checkSession()

    }

    private fun navigateToLogin(){

        val intent = Intent(this, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        finish()
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    101
                )
            }
        }
    }

}