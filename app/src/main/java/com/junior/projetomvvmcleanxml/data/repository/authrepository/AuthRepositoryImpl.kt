package com.junior.projetomvvmcleanxml.data.repository.authrepository

import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.junior.projetomvvmcleanxml.data.datasource.remote.FirebaseAuthDataSource
import com.junior.projetomvvmcleanxml.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val data: FirebaseAuthDataSource
) : AuthRepository {

    override suspend fun register(email: String, senha: String): Result<String> {
        return try {
            data.register(email, senha).fold(
                onSuccess = { Result.success(it) },
                onFailure = { e -> Result.failure(mapFirebaseError(e)) }
            )
        } catch (e: Exception) {
            Result.failure(mapFirebaseError(e))
        }
    }

    override suspend fun login(email: String, senha: String): Result<String> {
        return try {
            data.login(email, senha).fold(
                onSuccess = { Result.success(it) },
                onFailure = { e -> Result.failure(mapFirebaseError(e)) }
            )
        } catch (e: Exception) {
            Result.failure(mapFirebaseError(e))
        }
    }

    override fun logout() {
        data.logout()
    }

    private fun mapFirebaseError(e: Throwable): AuthError {
        if (e is FirebaseNetworkException) return AuthError.NoInternetConnection
        if (e is AuthError) return e
        if (e is FirebaseAuthException) return mapAuthException(e)

        return AuthError.Unknown(e)
    }

    private fun mapAuthException(e: FirebaseAuthException): AuthError {

        return when (e) {


            is FirebaseAuthInvalidCredentialsException -> AuthError.InvalidCredentials
            is FirebaseAuthInvalidUserException -> AuthError.InvalidCredentials

            else -> {
                when (e.errorCode) {
                    "ERROR_EMAIL_ALREADY_IN_USE" -> AuthError.EmailAlreadyInUse
                    "ERROR_INVALID_EMAIL" -> AuthError.InvalidEmail
                    "ERROR_WEAK_PASSWORD" -> AuthError.WeakPassword

                    "ERROR_USER_NOT_FOUND", "ERROR_WRONG_PASSWORD" -> AuthError.InvalidCredentials

                    else -> AuthError.Unknown(e)
                }
            }
        }
    }
}