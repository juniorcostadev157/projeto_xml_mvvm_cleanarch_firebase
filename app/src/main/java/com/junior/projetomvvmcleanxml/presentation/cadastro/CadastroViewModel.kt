package com.junior.projetomvvmcleanxml.presentation.cadastro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junior.projetomvvmcleanxml.domain.usecase.authenticationusecase.CreateRegisterValidationUseCase
import com.junior.projetomvvmcleanxml.domain.usecase.users.CreateUsersUseCase
import com.junior.projetomvvmcleanxml.domain.usecase.userpreference.SaveUserSessionUseCase
import com.junior.projetomvvmcleanxml.domain.usecase.users.GetUserByIdUseCase
import kotlinx.coroutines.launch

class CadastroViewModel (
    private val createUsers: CreateUsersUseCase,
    private val createRegister: CreateRegisterValidationUseCase,
    private val saveUserSessionUseCase: SaveUserSessionUseCase,
    private val getUserByIdUseCase: GetUserByIdUseCase

): ViewModel() {

    private val _cadastroState = MutableLiveData<CadastroUiState>(CadastroUiState.Empty)
    val cadastroState: LiveData<CadastroUiState> = _cadastroState

    fun cadastro(nome: String, email: String, password: String){
        _cadastroState.value = CadastroUiState.Loading

        viewModelScope.launch {
            val validation = createRegister(email, password)
            if (validation.success){


                validation.data?.let {userId->
                    createUsers(nome = nome, email = email, userId)
                    val infoUser = getUserByIdUseCase(validation.data)
                    infoUser?.let {
                        saveUserSessionUseCase(it.id ,  it.nome)
                    }

                }
                _cadastroState.value = CadastroUiState.Success

            }else{
                _cadastroState.value = CadastroUiState.Error(validation.errorMessage ?: "Erro desconhecido")
            }
        }
    }
}