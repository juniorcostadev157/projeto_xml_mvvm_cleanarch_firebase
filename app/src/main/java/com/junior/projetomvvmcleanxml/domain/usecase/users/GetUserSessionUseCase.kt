package com.junior.projetomvvmcleanxml.domain.usecase.users

import com.junior.projetomvvmcleanxml.domain.repository.UserSessionDataSource

class GetUserSessionUseCase(private val repository: UserSessionDataSource) {
    operator fun invoke(): String?{
        return repository.getUserId()
    }
}