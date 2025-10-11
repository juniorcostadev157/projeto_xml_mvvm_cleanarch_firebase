package com.junior.projetomvvmcleanxml.domain.usecase.users

import com.junior.projetomvvmcleanxml.domain.model.user.Users
import com.junior.projetomvvmcleanxml.domain.repository.UserRepository

class CreateUsersUseCase (
    private val repository: UserRepository
) {
    suspend operator  fun invoke(user: Users){
         repository.createUser(user)
    }
}