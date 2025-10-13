package com.junior.projetomvvmcleanxml.domain.repository

import com.junior.projetomvvmcleanxml.data.model.Item
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    suspend fun createItem(item: Item)

    fun getAllItem(): Flow<List<Item>>

}