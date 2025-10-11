package com.junior.projetomvvmcleanxml.domain.model.user

import com.junior.projetomvvmcleanxml.data.model.UserResponse

fun Users.toResponse() = UserResponse(
    id = this.id,
    nome = this.nome,
    email = this.email
)