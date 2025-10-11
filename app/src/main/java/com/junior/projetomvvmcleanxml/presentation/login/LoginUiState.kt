package com.junior.projetomvvmcleanxml.presentation.login

sealed class LoginUiState{

    object Loading: LoginUiState()
    object Success: LoginUiState()
    data class Error(val message: String): LoginUiState()
    object Empty: LoginUiState()
}



