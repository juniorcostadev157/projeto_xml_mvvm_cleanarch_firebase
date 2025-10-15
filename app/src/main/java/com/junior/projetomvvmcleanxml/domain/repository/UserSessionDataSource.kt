package com.junior.projetomvvmcleanxml.domain.repository

interface UserSessionDataSource {
    fun saveUserId(userId: String, name: String)
    fun getUserId(): String?
    fun getUserName(): String?
    fun clear()
}
