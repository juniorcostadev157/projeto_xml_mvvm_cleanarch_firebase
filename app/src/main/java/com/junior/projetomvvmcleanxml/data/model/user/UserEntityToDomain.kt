package com.junior.projetomvvmcleanxml.data.model.user

import com.junior.projetomvvmcleanxml.domain.model.user.Users

fun UserEntity.toDomain() = Users(
    id = this.id,
    email = this.email,
    nome = this.nome ?: "nome não informado"
)


