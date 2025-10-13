package com.junior.projetomvvmcleanxml.data.repository

import com.junior.projetomvvmcleanxml.data.datasource.FirebaseItemDataSource
import com.junior.projetomvvmcleanxml.data.model.item.toDomain
import com.junior.projetomvvmcleanxml.domain.model.item.Item
import com.junior.projetomvvmcleanxml.domain.model.item.toEntity
import com.junior.projetomvvmcleanxml.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ItemRepositoryImpl (private val data: FirebaseItemDataSource): ItemRepository {


    override suspend fun createItem(item: Item) {
        data.createItem(item.toEntity())
    }

    override fun getAllItem(): Flow<List<Item>> {
        return data.getAllItem().map { list->
           list.map { itemEntity->
               itemEntity.toDomain()
           }
       }
    }
}