package com.junior.projetomvvmcleanxml.presentation.principal.createitem


sealed class CreateItemUiState {
    object Success: CreateItemUiState()
    object Loading: CreateItemUiState()
    object Empty: CreateItemUiState()
    data class Error(val message: String): CreateItemUiState()
}