package com.junior.projetomvvmcleanxml.domain.usecase.authenticationusecase

import com.junior.projetomvvmcleanxml.data.repository.authrepository.AuthError
import com.junior.projetomvvmcleanxml.domain.repository.AuthRepository

class CreateRegisterValidationUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): ValidationResult{

        if (email.isBlank() || !email.contains("@")){
            return ValidationResult(false, "Email Invalido")
        }

        if (password.length < 6){
            return ValidationResult(false, "Senha tem que ter pelo menos 6 caracteres")
        }
        val result  = repository.register(email, password)

        return if (result.isSuccess){
            ValidationResult(true)
        }else{
            ValidationResult(false, (result.exceptionOrNull() as? AuthError)?.messageError)
        }

    }
}