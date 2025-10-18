package com.junior.projetomvvmcleanxml.presentation.principal.list_item_room_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junior.projetomvvmcleanxml.domain.usecase.item.ListLocalItemsUseCase
import com.junior.projetomvvmcleanxml.presentation.principal.list_item_firebase_fragment.ListItemUiState
import kotlinx.coroutines.launch

class ListRoomViewModel(
    private val listLocal: ListLocalItemsUseCase
) : ViewModel(){

    private val _uiState = MutableLiveData<ListItemUiState>()
    val uiState: LiveData<ListItemUiState> = _uiState

    init {
        loadItems()
    }

    fun loadItems(){
        viewModelScope.launch {
            _uiState.value = ListItemUiState.Loading

            try {
               listLocal().collect {items->
                   if (items.isEmpty()){
                       _uiState.value = ListItemUiState.Empty
                   }else{
                       _uiState.value = ListItemUiState.Success(items)
                   }

               }


            }catch (e: Exception){
                _uiState.value = ListItemUiState.Error(e.message ?: "Erro desconhecido")

            }
        }
    }
}