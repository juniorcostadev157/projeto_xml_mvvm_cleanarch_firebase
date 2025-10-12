package com.junior.projetomvvmcleanxml.data.datasource

import com.google.firebase.firestore.FirebaseFirestore
import com.junior.projetomvvmcleanxml.data.model.UserResponse
import kotlinx.coroutines.tasks.await

class FirebaseUserDataSource (
    private val firestore: FirebaseFirestore
) {

    suspend fun createUser(user: UserResponse){
        try {
            val docRef = firestore.collection("users").document()
            val userWithId = user.copy(id = docRef.id)
            docRef.set(userWithId).await()
        }catch (e: Exception){
            throw e
        }
    }


}