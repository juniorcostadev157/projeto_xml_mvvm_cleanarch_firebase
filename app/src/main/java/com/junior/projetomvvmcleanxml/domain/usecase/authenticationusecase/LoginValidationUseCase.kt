package com.junior.projetomvvmcleanxml.domain.usecase.authenticationusecase


import com.junior.projetomvvmcleanxml.data.repository.authrepository.AuthError
import com.junior.projetomvvmcleanxml.domain.repository.AuthRepository

class LoginValidationUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String , password: String): ValidationResult{

        if (email.isBlank() || !email.contains("@")){
            return ValidationResult(false, "Email Invalido")
        }

        if (password.isBlank()){
            return ValidationResult(false, "Preencha o Senha")
        }

        val result = repository.login(email, password)
        return if (result.isSuccess){
            val useId = result.getOrNull()
            ValidationResult(true, data = useId)

        }else{

            ValidationResult(false, (result.exceptionOrNull() as? AuthError)?.messageError)
        }
        }


    }
