package com.junior.projetomvvmcleanxml.domain.usecase.authenticationusecase

import com.junior.projetomvvmcleanxml.domain.repository.AuthRepository


class LogoutUseCase(private val repository: AuthRepository) {

    operator fun invoke(){
        repository.logout()
    }

}