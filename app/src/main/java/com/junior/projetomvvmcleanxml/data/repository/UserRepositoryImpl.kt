package com.junior.projetomvvmcleanxml.data.repository

import com.junior.projetomvvmcleanxml.data.datasource.remote.FirebaseUserDataSource
import com.junior.projetomvvmcleanxml.data.model.user.toDomain
import com.junior.projetomvvmcleanxml.domain.model.user.Users
import com.junior.projetomvvmcleanxml.domain.model.user.toResponse
import com.junior.projetomvvmcleanxml.domain.repository.UserRepository

class UserRepositoryImpl (
    private val data: FirebaseUserDataSource
): UserRepository {
    override suspend fun createUser(user: Users) {

        data.createUser(user.toResponse())
    }

    override suspend fun getUserById(userId: String): Users? {
        return data.getUserById(userId)?.toDomain()
    }
}