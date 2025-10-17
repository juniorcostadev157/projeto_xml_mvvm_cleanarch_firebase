package com.junior.projetomvvmcleanxml.domain.model.item

import com.junior.projetomvvmcleanxml.data.model.item.ItemEntity

fun Item.toEntity() = ItemEntity(
    id = this.id,
    name = this.name,
    createdBy = this.createdBy
)