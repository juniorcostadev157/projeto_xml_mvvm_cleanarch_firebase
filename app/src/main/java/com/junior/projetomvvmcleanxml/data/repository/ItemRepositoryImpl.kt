package com.junior.projetomvvmcleanxml.data.repository

import com.junior.projetomvvmcleanxml.data.datasource.FirebaseItemDataSource
import com.junior.projetomvvmcleanxml.data.model.Item
import com.junior.projetomvvmcleanxml.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow

class ItemRepositoryImpl (data: FirebaseItemDataSource): ItemRepository {
    override suspend fun createItem(item: Item) {

    }

    override fun getAllItem(): Flow<List<Item>> {
        TODO("Not yet implemented")
    }
}