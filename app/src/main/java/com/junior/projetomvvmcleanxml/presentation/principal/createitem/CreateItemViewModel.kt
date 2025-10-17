package com.junior.projetomvvmcleanxml.presentation.principal.createitem


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junior.projetomvvmcleanxml.core.AnalyticsLogger
import com.junior.projetomvvmcleanxml.domain.usecase.item.CreateItemUseCase
import com.junior.projetomvvmcleanxml.domain.usecase.userpreference.GetUserIdSessionUseCase
import kotlinx.coroutines.launch

class CreateItemViewModel(
    private val creteItem: CreateItemUseCase,
    private val getUserIdSessionUseCase: GetUserIdSessionUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<CreateItemUiState>(CreateItemUiState.Empty)
    val uiState: LiveData<CreateItemUiState> = _uiState

    fun createItem(name: String){
        viewModelScope.launch {
           try {
               val userId = getUserIdSessionUseCase()

               _uiState.value = CreateItemUiState.Loading
               if (userId.isNullOrBlank()){
                   _uiState.value = CreateItemUiState.Error("Usuário não logado")

                   AnalyticsLogger.logEvent(
                       "create_item_error",
                       mapOf("item_name" to name )
                   )
                   return@launch

               }


               creteItem(name,userId)
               _uiState.value = CreateItemUiState.Success
               _uiState.value = CreateItemUiState.Empty
               AnalyticsLogger.logEvent(
                   "create_item_success",
                   mapOf("user_id" to userId, "item_name" to name )
               )

           }catch (e: Exception){
               _uiState.value = CreateItemUiState.Error(e.message ?: "Erro desconhecido")
               _uiState.value = CreateItemUiState.Empty
           }
        }
    }

}