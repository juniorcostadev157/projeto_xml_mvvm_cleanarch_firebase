package com.junior.projetomvvmcleanxml.presentation.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GenericViewModelFactory<T: ViewModel>(
    private val creator: () -> T
): ViewModelProvider.Factory{

    override fun <V : ViewModel> create (modelClass: Class<V>): V {

        // 1. Cria a instância da ViewModel (o único lugar que a creator deve ser chamada).
        val createdViewModel = creator()

        // 2. Verifica se a classe pedida (modelClass) é compatível com a classe que criamos.
        if (modelClass.isAssignableFrom(createdViewModel.javaClass)){
            @Suppress("UNCHECKED_CAST")
            return createdViewModel as V
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}