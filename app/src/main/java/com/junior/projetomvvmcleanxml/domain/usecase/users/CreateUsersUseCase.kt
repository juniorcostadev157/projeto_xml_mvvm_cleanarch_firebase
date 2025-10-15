package com.junior.projetomvvmcleanxml.domain.usecase.users

import com.junior.projetomvvmcleanxml.domain.model.user.Users
import com.junior.projetomvvmcleanxml.domain.repository.UserRepository

class CreateUsersUseCase (
    private val repository: UserRepository
) {
    suspend operator  fun invoke(nome: String, email: String, id: String){

        val user = Users(
            id = id,
            nome  = nome,
            email = email
        )
         repository.createUser(user)
    }
}