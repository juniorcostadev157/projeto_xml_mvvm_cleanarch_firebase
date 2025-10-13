package com.junior.projetomvvmcleanxml.data.model.item

import com.junior.projetomvvmcleanxml.domain.model.item.Item

fun ItemEntity.toDomain() = Item(
    id = this.id,
    name = this.name
)