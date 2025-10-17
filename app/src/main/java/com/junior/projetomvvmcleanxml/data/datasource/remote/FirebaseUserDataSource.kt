package com.junior.projetomvvmcleanxml.data.datasource.remote


import com.google.firebase.firestore.FirebaseFirestore
import com.junior.projetomvvmcleanxml.core.CrashlyticsLogger
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

    suspend fun getUserById(userId: String): UserEntity? {
        return try {
            val querySnapshot = firestore.collection("users")
                .whereEqualTo("id", userId)
                .get()
                .await()

            querySnapshot.toObjects(UserEntity::class.java).firstOrNull()
        } catch (e: Exception) {
            // Adiciona contexto útil
            CrashlyticsLogger.setUserId(userId)
            CrashlyticsLogger.setCustomKey("data_source", "FirebaseUserDataSource")
            CrashlyticsLogger.logCrash(e)

            // Retorna null para o app continuar funcionando
           null
        }
    }






}