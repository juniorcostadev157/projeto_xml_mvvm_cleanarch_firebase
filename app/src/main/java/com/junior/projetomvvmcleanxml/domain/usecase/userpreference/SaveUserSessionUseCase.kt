package com.junior.projetomvvmcleanxml.domain.usecase.userpreference

import com.junior.projetomvvmcleanxml.domain.repository.UserSessionDataSource

class SaveUserSessionUseCase(private val repository: UserSessionDataSource) {

    operator fun invoke(userId: String, name: String) {
        repository.saveUserId(userId, name)
    }
}