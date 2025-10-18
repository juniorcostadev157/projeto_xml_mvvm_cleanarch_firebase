package com.junior.projetomvvmcleanxml.data.datasource.local.room

import com.junior.projetomvvmcleanxml.data.model.item.ItemLocalEntity

class RoomItemDataSource(private val dao: ItemDao) {

    suspend fun insertItem(item: ItemLocalEntity){
        dao.insertItem(item)
    }

    suspend fun getAllItems(): List<ItemLocalEntity?>{
        return dao.getAllItems()
    }

    suspend fun getPendingItems(): List<ItemLocalEntity?>{
        return dao.getPendingItems()
    }

    suspend fun updateItem(item: ItemLocalEntity){
        dao.updateItem(item)
    }

    suspend fun clearAll(){
        dao.clearAll()
    }


}