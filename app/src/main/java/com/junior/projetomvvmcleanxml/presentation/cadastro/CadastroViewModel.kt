package com.junior.projetomvvmcleanxml.presentation.cadastro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junior.projetomvvmcleanxml.domain.usecase.authenticationusecase.CreateRegisterValidationUseCase
import com.junior.projetomvvmcleanxml.domain.usecase.users.CreateUsersUseCase
import kotlinx.coroutines.launch

class CadastroViewModel (
    private val createUsers: CreateUsersUseCase,
    private val createRegister: CreateRegisterValidationUseCase
): ViewModel() {

    private val _cadastroState = MutableLiveData<CadastroUiState>(CadastroUiState.Empty)
    val cadastroState: LiveData<CadastroUiState> = _cadastroState

    fun cadastro(nome: String, email: String, password: String){
        _cadastroState.value = CadastroUiState.Loading

        viewModelScope.launch {
            val validation = createRegister(email, password)
            if (validation.success){
                _cadastroState.value = CadastroUiState.Success
                createUsers(nome = nome, email = email)
            }else{
                _cadastroState.value = CadastroUiState.Error(validation.errorMessage ?: "Erro desconhecido")
            }
        }
    }
}