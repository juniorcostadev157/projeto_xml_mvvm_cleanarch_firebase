package com.junior.projetomvvmcleanxml.domain.model.item

import com.junior.projetomvvmcleanxml.data.model.item.ItemEntity
import com.junior.projetomvvmcleanxml.data.model.item.ItemLocalEntity
import java.util.UUID

fun Item.toEntity() = ItemEntity(
    id = this.id,
    name = this.name,
    createdBy = this.createdBy
)

fun Item.toLocalEntity(isSynchronized: Boolean) = ItemLocalEntity(
    id = this.id.ifBlank { UUID.randomUUID().toString() },
    name = this.name,
    createdBy = this.createdBy,
    isSynchronized = isSynchronized
)

