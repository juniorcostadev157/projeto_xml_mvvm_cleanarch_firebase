package com.junior.projetomvvmcleanxml.domain.repository

interface UserSessionDataSource {
    fun saveUserId(userId: String)
    fun getUserId(): String?
    fun clear()
}
