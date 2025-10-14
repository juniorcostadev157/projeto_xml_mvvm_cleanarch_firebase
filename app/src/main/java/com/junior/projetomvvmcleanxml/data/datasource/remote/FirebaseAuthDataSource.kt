package com.junior.projetomvvmcleanxml.data.datasource.remote

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class FirebaseAuthDataSource(
    private val firebase: FirebaseAuth,

    ) {
    suspend fun login(email: String, password: String): Result<String>{
        return try {
            val result = firebase.signInWithEmailAndPassword(email, password).await()
            val userId = result.user?.uid ?:return Result.failure(Exception("User ID is null"))
            Result.success(userId)

        }catch (e: Exception){
            Result.failure(e)
        }
    }

    suspend fun register(email: String, password: String): Result<String>{
        return try {

            val result = firebase.createUserWithEmailAndPassword(email, password).await()

            val userId = result.user?.uid ?: return Result.failure(Exception("User ID null"))
            Result.success(userId)

        }catch (e: Exception){
            Result.failure(e)
        }
    }

    fun logout(){
        firebase.signOut()
    }

    fun isUserLoggedIn(): Boolean{
        return firebase.currentUser != null
    }
}