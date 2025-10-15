package com.junior.projetomvvmcleanxml.presentation.cadastro

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
import com.junior.projetomvvmcleanxml.databinding.ActivityCadastroBinding
import com.junior.projetomvvmcleanxml.presentation.login.SessionViewModel
import com.junior.projetomvvmcleanxml.presentation.principal.MainScreenActivity
import com.junior.projetomvvmcleanxml.presentation.utils.InjectContainer

class CadastroActivity : AppCompatActivity() {

    private lateinit var viewModel: CadastroViewModel
    private lateinit var binding: ActivityCadastroBinding
    private lateinit var sessionViewModel: SessionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        enableEdgeToEdge()

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val factory = InjectContainer.cadastroFactory
        viewModel = ViewModelProvider(this, factory)[CadastroViewModel::class.java]
        sessionViewModel = ViewModelProvider(this, InjectContainer.sessionFactory)[SessionViewModel::class.java]



        setupObservers()
        cadastrarUser()
        backButton()
    }

   private fun setupObservers(){
       viewModel.cadastroState.observe(this){state->

           when(state){
               is CadastroUiState.Empty -> hideLoading()
               is CadastroUiState.Loading -> showLoading()
               is CadastroUiState.Success ->{
                   hideLoading()
                   navigateToHome()
               }

               is CadastroUiState.Error -> {
                   hideLoading()
                   showToast(state.message)
               }
           }
       }
   }



    private fun cadastrarUser(){
        binding.btnCadastrar.setOnClickListener {
            hideKeyboard()
            val nome = binding.etNomeCadastro.text.toString()
            val email = binding.etEmailCadastro.text.toString().trim()
            val password = binding.etPasswordCadastro.text.toString().trim()
            val confirmPassword = binding.etConfirmPasswordCadastro.text.toString().trim()

            if (nome.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank()){
                showToast("Preencha todos os campos")}
            else if (password != confirmPassword){
                showToast("Senhas não conferem")
            }else{
                viewModel.cadastro(nome, email, confirmPassword)
            }
        }
    }

    private fun showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(){
        binding.progressLoadingCadastro.visibility = View.VISIBLE
        binding.btnCadastrar.isEnabled = false
        binding.btnCadastrar.alpha = 0.6f
    }
    private fun hideLoading(){
        binding.progressLoadingCadastro.visibility = View.GONE
        binding.btnCadastrar.isEnabled = true
        binding.btnCadastrar.alpha = 1.0f
    }

    private fun navigateToHome(){
        val intent = Intent(this, MainScreenActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun backButton(){
        binding.ibBackCadastro.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }


}