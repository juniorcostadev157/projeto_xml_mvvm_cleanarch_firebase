package com.junior.projetomvvmcleanxml.presentation.cadastro

sealed class CadastroUiState {
    object Loading: CadastroUiState()
    object Success: CadastroUiState()
    data class Error(val message: String): CadastroUiState()
    object Empty: CadastroUiState()

}
