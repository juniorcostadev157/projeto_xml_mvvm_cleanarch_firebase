package com.junior.projetomvvmcleanxml.domain.usecase.userpreference

import com.junior.projetomvvmcleanxml.domain.repository.UserSessionDataSource

class GetNameSessionUseCase(private val repository: UserSessionDataSource) {

    operator fun invoke(): String?{
        return repository.getUserName()
    }
}