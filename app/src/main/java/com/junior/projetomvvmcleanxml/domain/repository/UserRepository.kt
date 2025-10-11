package com.junior.projetomvvmcleanxml.domain.repository


import com.junior.projetomvvmcleanxml.domain.model.user.Users


interface UserRepository {

   suspend fun createUser(user: Users)

}