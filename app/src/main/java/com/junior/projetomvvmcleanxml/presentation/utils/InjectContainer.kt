package com.junior.projetomvvmcleanxml.presentation.utils


import com.google.firebase.auth.FirebaseAuth
import com.junior.projetomvvmcleanxml.data.datasource.FirebaseAuthDataSource
import com.junior.projetomvvmcleanxml.data.repository.authrepository.AuthRepositoryImpl
import com.junior.projetomvvmcleanxml.domain.usecase.authenticationusecase.LoginValidationUseCase
import com.junior.projetomvvmcleanxml.presentation.login.LoginViewModel

object InjectContainer {

    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val authDataSource by lazy { FirebaseAuthDataSource(firebaseAuth) }
    private val authRepository by lazy { AuthRepositoryImpl(authDataSource) }
    private val loginValidationUseCase by lazy { LoginValidationUseCase(authRepository) }

    val loginFactory: GenericViewModelFactory<LoginViewModel> by lazy {
        GenericViewModelFactory { LoginViewModel(loginValidationUseCase) }
    }
}

