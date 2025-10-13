package com.junior.projetomvvmcleanxml.data.datasource

import com.google.firebase.firestore.FirebaseFirestore
import com.junior.projetomvvmcleanxml.data.model.item.ItemEntity
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class FirebaseItemDataSource (
    private val firestore: FirebaseFirestore
) {

   suspend fun createItem(item: ItemEntity){
        try {
            val  docRef = firestore.collection("item").document()
            val itemWithId = item.copy(id = docRef.id)
            docRef.set(itemWithId).await()
        }catch (e: Exception){
            throw e
        }
    }


      fun getAllItem(): Flow< List<ItemEntity>> = callbackFlow{

           val listener =firestore.collection("item").addSnapshotListener { snapshot, error ->
                if (error !=null){
                return@addSnapshotListener
                }

                if (snapshot !=null){
                  val list = snapshot.documents.mapNotNull { doc->
                    doc.toObject(ItemEntity::class.java)?.apply { id = doc.id }

                 }
                    trySend(list).isSuccess
                }

            }
          awaitClose { listener.remove() }
      }



    }
