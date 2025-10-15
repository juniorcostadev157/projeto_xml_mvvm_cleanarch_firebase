package com.junior.projetomvvmcleanxml.domain.usecase.userpreference

import com.junior.projetomvvmcleanxml.domain.repository.UserSessionDataSource

class GetUserIdSessionUseCase(private val repository: UserSessionDataSource) {
    operator fun invoke(): String?{
        return repository.getUserId()
    }
}