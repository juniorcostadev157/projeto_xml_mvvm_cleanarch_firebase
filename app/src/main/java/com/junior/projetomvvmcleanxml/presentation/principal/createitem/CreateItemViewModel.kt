package com.junior.projetomvvmcleanxml.presentation.principal.createitem


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junior.projetomvvmcleanxml.domain.usecase.item.CreateItemUseCase
import kotlinx.coroutines.launch

class CreateItemViewModel(private val creteItem: CreateItemUseCase) : ViewModel() {

    private val _uiState = MutableLiveData<CreateItemUiState>(CreateItemUiState.Empty)
    val uiState: LiveData<CreateItemUiState> = _uiState

    fun createItem(name: String){
        viewModelScope.launch {
           try {
               _uiState.value = CreateItemUiState.Loading
               creteItem(name)
               _uiState.value = CreateItemUiState.Success
               _uiState.value = CreateItemUiState.Empty
           }catch (e: Exception){
               _uiState.value = CreateItemUiState.Error(e.message ?: "Erro desconhecido")
               _uiState.value = CreateItemUiState.Empty
           }
        }
    }

}