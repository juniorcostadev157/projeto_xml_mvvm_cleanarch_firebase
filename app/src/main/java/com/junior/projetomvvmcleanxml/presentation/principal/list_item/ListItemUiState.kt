package com.junior.projetomvvmcleanxml.presentation.principal.list_item

import com.junior.projetomvvmcleanxml.domain.model.item.Item

sealed class ListItemUiState {
    object Loading: ListItemUiState()
    object Empty: ListItemUiState()
    data class Success(val items: List<Item>): ListItemUiState()
    data class Error(val message: String): ListItemUiState()
}