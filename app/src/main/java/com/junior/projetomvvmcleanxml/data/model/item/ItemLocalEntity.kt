package com.junior.projetomvvmcleanxml.data.model.item

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "items")
data class ItemLocalEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val createdBy: String?,
    val isSynchronized: Boolean = false
)