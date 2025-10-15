package com.junior.projetomvvmcleanxml.domain.usecase.authenticationusecase

data class ValidationResult(
    val success: Boolean,
    val errorMessage: String? = null,
    val data: String? = null
)
