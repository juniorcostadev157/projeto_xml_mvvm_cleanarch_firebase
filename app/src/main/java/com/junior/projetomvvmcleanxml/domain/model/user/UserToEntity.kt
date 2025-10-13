package com.junior.projetomvvmcleanxml.domain.model.user

import com.junior.projetomvvmcleanxml.data.model.UserEntity

fun Users.toResponse() = UserEntity(
    id = this.id,
    nome = this.nome,
    email = this.email
)