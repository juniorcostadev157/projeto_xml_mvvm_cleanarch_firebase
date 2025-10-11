package com.junior.projetomvvmcleanxml.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junior.projetomvvmcleanxml.domain.usecase.authenticationusecase.LoginValidationUseCase
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginValidationUseCase
): ViewModel() {

    private val _loginStage = MutableLiveData<LoginUiState>(LoginUiState.Empty)
    val loginStage: LiveData<LoginUiState> = _loginStage


    fun login(email: String, senha: String){
        _loginStage.value = LoginUiState.Loading

        viewModelScope.launch {
            val  validation = loginUseCase(email, senha)
            if (validation.success){
                _loginStage.value = LoginUiState.Success
            }else{
                _loginStage.value = LoginUiState.Error(validation.errorMessage ?: "Erro desconhecido")

            }


        }
    }

}