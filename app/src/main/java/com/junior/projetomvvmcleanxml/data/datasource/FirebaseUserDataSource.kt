package com.junior.projetomvvmcleanxml.data.datasource

import com.google.firebase.firestore.FirebaseFirestore
import com.junior.projetomvvmcleanxml.data.model.UserResponse
import kotlinx.coroutines.tasks.await

class FirebaseUserDataSource (
    private val firestore: FirebaseFirestore
) {

    suspend fun createUser(user: UserResponse){
        try {
            firestore.collection("users").document().set(user).await()
        }catch (e: Exception){
            throw e
        }
    }


}