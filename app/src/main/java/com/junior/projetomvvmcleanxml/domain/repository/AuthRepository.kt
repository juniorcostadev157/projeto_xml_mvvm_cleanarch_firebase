package com.junior.projetomvvmcleanxml.domain.repository

interface AuthRepository {

   suspend fun register(email: String, senha: String) : Result<String>
   suspend fun login(email: String, senha: String): Result<String>
   fun logout()
}