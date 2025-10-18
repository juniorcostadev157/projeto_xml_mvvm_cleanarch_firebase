package com.junior.projetomvvmcleanxml.data.repository

import com.junior.projetomvvmcleanxml.data.datasource.local.room.RoomItemDataSource
import com.junior.projetomvvmcleanxml.data.datasource.remote.FirebaseItemDataSource
import com.junior.projetomvvmcleanxml.data.model.item.toDomain
import com.junior.projetomvvmcleanxml.domain.model.item.Item
import com.junior.projetomvvmcleanxml.domain.model.item.toEntity
import com.junior.projetomvvmcleanxml.domain.model.item.toLocalEntity
import com.junior.projetomvvmcleanxml.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.lang.Exception

class ItemRepositoryImpl (
    private val remote: FirebaseItemDataSource,
    private val local: RoomItemDataSource
): ItemRepository {


    override suspend fun createItem(item: Item) {

      local.insertItem(item.toLocalEntity(isSynchronized = false))
    }

    override fun getAllItem(): Flow<List<Item>> {
       return remote.getAllItem().map {list->
           list.map { itemEntity ->
               itemEntity.toDomain()
           }

       }
    }

    override suspend fun getAllLocalItems(): Flow<List<Item>> {
        return local.getAllItems().map { list->
            list.map{itemEntityLocal->
                itemEntityLocal!!.toDomain()
            }
        }





    }

    suspend fun syncPendingItems(){
        val pendentes = local.getPendingItems()
        for (item in pendentes) {
            try {
                remote.createItem(item?.toDomain()!!.toEntity())
                local.updateItem(item.copy(isSynchronized = true))
            } catch (e: Exception) {
                println("Erro ao sincronizar ${item?.id}: ${e.message}")
            }
        }
    }
}