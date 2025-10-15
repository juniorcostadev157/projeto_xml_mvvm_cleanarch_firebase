package com.junior.projetomvvmcleanxml.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.junior.projetomvvmcleanxml.core.hideKeyboard
import com.junior.projetomvvmcleanxml.databinding.ActivityLoginBinding
import com.junior.projetomvvmcleanxml.presentation.cadastro.CadastroActivity
import com.junior.projetomvvmcleanxml.presentation.principal.MainScreenActivity
import com.junior.projetomvvmcleanxml.presentation.utils.InjectContainer


class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var sessionViewModel: SessionViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        InjectContainer.init(applicationContext)



        val factory = InjectContainer.loginFactory
        viewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]
        sessionViewModel = ViewModelProvider(this, InjectContainer.sessionFactory)[SessionViewModel::class.java]

        buttonLogin()
        setupObservers()
        navigateToCadastro()

    }

    override fun onStart() {
        super.onStart()
        sessionViewModel.checkSession()
    }

    private fun setupObservers() {
        viewModel.loginStage.observe(this) { state ->
            when (state) {
                is LoginUiState.Empty -> hideLoading()
                is LoginUiState.Loading -> showLoading()
                is LoginUiState.Success -> {
                    hideLoading()
                    navigateToHome()
                }
                is LoginUiState.Error -> {
                    hideLoading()
                    showToast(state.message)
                }
            }
        }

        sessionViewModel.isLoggedIn.observe(this) { isLogged ->
            if (isLogged) {
                navigateToHome()
                finish()
            }
        }
    }

    private fun buttonLogin(){

        binding.btnLogin.setOnClickListener {
            hideKeyboard()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.login(email, password)
            }
        }
    }

    private fun showLoading() {
        binding.progressLoading.visibility = View.VISIBLE
        binding.btnLogin.isEnabled = false
        binding.btnLogin.alpha = 0.6f
    }

    private fun hideLoading() {
        binding.progressLoading.visibility = View.GONE
        binding.btnLogin.isEnabled = true
        binding.btnLogin.alpha = 1.0f
    }

    private fun navigateToHome() {
        val intent = Intent(this, MainScreenActivity::class.java)
        startActivity(intent)
        finish()

    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToCadastro(){
        binding.tvRegisterLink.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
    }
}
