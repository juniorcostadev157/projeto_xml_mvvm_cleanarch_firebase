package com.junior.projetomvvmcleanxml.data.datasource.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.junior.projetomvvmcleanxml.data.model.item.ItemLocalEntity
@Dao
interface ItemDao {

    @Query("SELECT * FROM items")
    suspend fun getAllItems(): List<ItemLocalEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ItemLocalEntity)

    @Query("SELECT * FROM items WHERE isSynchronized = 0")
    suspend fun getPendingItems(): List<ItemLocalEntity?>

    @Update
    suspend fun updateItem(item: ItemLocalEntity)

    @Query("DELETE FROM items")
    suspend fun clearAll()

}