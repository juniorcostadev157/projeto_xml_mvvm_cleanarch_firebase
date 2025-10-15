package com.junior.projetomvvmcleanxml.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.junior.projetomvvmcleanxml.domain.usecase.userpreference.GetNameSessionUseCase
import com.junior.projetomvvmcleanxml.domain.usecase.userpreference.GetUserIdSessionUseCase
import com.junior.projetomvvmcleanxml.domain.usecase.userpreference.SaveUserSessionUseCase

class SessionViewModel(
    private val getUserSession: GetUserIdSessionUseCase,
    private val saveUserSession: SaveUserSessionUseCase,
    private val getNameSessionUseCase: GetNameSessionUseCase
  ): ViewModel() {

    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean> = _isLoggedIn

    fun checkSession(){
        _isLoggedIn.value = !getUserSession().isNullOrEmpty()
    }

    fun login(userId: String, name: String){
        saveUserSession(userId, name)
        _isLoggedIn.value = true
    }

    fun getNomeUserSession(): String{
        return getNameSessionUseCase().let {
            it ?: "sem nome"
        }
    }




}