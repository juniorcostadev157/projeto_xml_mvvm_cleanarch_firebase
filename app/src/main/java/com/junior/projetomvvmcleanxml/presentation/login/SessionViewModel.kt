package com.junior.projetomvvmcleanxml.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.junior.projetomvvmcleanxml.domain.usecase.users.ClearUseSessionUseCase
import com.junior.projetomvvmcleanxml.domain.usecase.users.GetUserSessionUseCase
import com.junior.projetomvvmcleanxml.domain.usecase.users.SaveUserSessionUseCase

class SessionViewModel(
    private val getUserSession: GetUserSessionUseCase,
    private val saveUserSession: SaveUserSessionUseCase,
    private val clearUseSession: ClearUseSessionUseCase
): ViewModel() {

    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean> = _isLoggedIn

    fun checkSession(){
        _isLoggedIn.value = !getUserSession().isNullOrEmpty()
    }

    fun login(userId: String){
        saveUserSession(userId)
        _isLoggedIn.value = true
    }

    fun logout(){
        clearUseSession()
        _isLoggedIn.value = false
    }


}