package com.junior.projetomvvmcleanxml.data.datasource.remote


import com.google.firebase.firestore.FirebaseFirestore
import com.junior.projetomvvmcleanxml.data.model.user.UserEntity
import kotlinx.coroutines.tasks.await

class FirebaseUserDataSource (
    private val firestore: FirebaseFirestore
) {

    suspend fun createUser(user: UserEntity){
        try {
            val docRef = firestore.collection("users").document(user.id)
            docRef.set(user).await()
        }catch (e: Exception){
            throw e
        }
    }

     suspend fun getUserById(userId: String):UserEntity?{



        val querySnapshot = firestore.collection("users")
            .whereEqualTo("id", userId).get().await()

         val user =querySnapshot.toObjects(UserEntity::class.java).firstOrNull()



         return user

    }





}