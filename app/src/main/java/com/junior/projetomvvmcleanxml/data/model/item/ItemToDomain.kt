package com.junior.projetomvvmcleanxml.data.model.item

import com.junior.projetomvvmcleanxml.domain.model.item.Item

fun ItemEntity.toDomain() = Item(
    id = this.id,
    name = this.name,
    createdBy = this.createdBy
)

fun ItemLocalEntity.toDomain() = Item(
    id = this.id,
    name = this.name,
    createdBy = this.createdBy,
    isSynchronized = this.isSynchronized
)