package com.junior.projetomvvmcleanxml.data.repository.authrepository

sealed class AuthError : Exception() {
    open val messageError: String = "Erro desconhecido"

    object InvalidCredentials : AuthError() {
        override val messageError: String = "Email ou senha inválidas"
    }

    object EmailAlreadyInUse : AuthError() {
        override val messageError: String = "Email já cadastrado"
    }

    object InvalidEmail : AuthError() {
        override val messageError: String = "Email inválido"
    }

    object WeakPassword : AuthError() {
        override val messageError: String = "Senha fraca"
    }

    object NoInternetConnection : AuthError() {
        override val messageError: String = "Sem conexão com a internet"
    }

    data class Unknown(val error: Throwable) : AuthError() {
        // tenta usar a mensagem da exceção, senão fallback
        override val messageError: String = error.message?.takeIf { it.isNotBlank() } ?: "Ocorreu um erro inesperado"
    }
}
