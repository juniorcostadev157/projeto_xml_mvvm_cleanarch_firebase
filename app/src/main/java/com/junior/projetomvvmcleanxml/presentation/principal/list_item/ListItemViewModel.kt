package com.junior.projetomvvmcleanxml.presentation.principal.list_item



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junior.projetomvvmcleanxml.domain.usecase.authenticationusecase.LogoutUseCase
import com.junior.projetomvvmcleanxml.domain.usecase.item.ListItemUseCase
import com.junior.projetomvvmcleanxml.domain.usecase.userpreference.ClearUseSessionUseCase
import kotlinx.coroutines.launch

class ListItemViewModel(
    private val getAllItem: ListItemUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val clearUseSessionUseCase: ClearUseSessionUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<ListItemUiState>()
    val uiState: LiveData<ListItemUiState> = _uiState

    init {
        loadItems()
    }

    fun loadItems(){
        viewModelScope.launch {
            _uiState.value = ListItemUiState.Loading
            try {
                getAllItem().collect {items->

                    if (items.isEmpty()){
                        _uiState.value = ListItemUiState.Empty
                    }else{
                        _uiState.value = ListItemUiState.Success(items)
                    }
                }
            }catch (e: Exception){
                _uiState.value = ListItemUiState.Error(e.message ?: "Erro ao carregar itens")
            }
        }
    }

    fun logout(){
        logoutUseCase()
        clearUseSessionUseCase()
    }




}