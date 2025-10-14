package com.junior.projetomvvmcleanxml.domain.usecase.users

import com.junior.projetomvvmcleanxml.domain.repository.UserSessionDataSource

class SaveUserSessionUseCase(private val repository: UserSessionDataSource) {

    operator fun invoke(userId: String) {
        repository.saveUserId(userId)
    }
}

