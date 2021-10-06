package io.hanyoungpark.metaverse.services

import android.accounts.AuthenticatorException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import io.hanyoungpark.metaverse.models.User
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

sealed class AuthenticateState {
    data class Success(val user: User): AuthenticateState()
    data class Error(val exception: Throwable): AuthenticateState()
}

interface AuthenticateService {
    fun fetchUser(): Flow<AuthenticateState>
    fun signUpByEmailAndPassword(email: String, password: String): Flow<AuthenticateState>
    fun loginByEmailAndPassword(email: String, password: String): Flow<AuthenticateState>
}

class AuthenticateServiceImpl @Inject constructor(): AuthenticateService {
    private val firebaseAuth: FirebaseAuth = Firebase.auth

    override fun fetchUser() = flow<AuthenticateState> {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            val emailFromFirebase = firebaseUser.email ?: ""
            emit(AuthenticateState.Success(User(emailFromFirebase)))
        } else {
            emit(AuthenticateState.Error(AuthenticatorException()))
        }
    }

    override fun signUpByEmailAndPassword(email: String, password: String) = callbackFlow {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            val user = task.result.user
            if (task.isSuccessful && user != null) {
                trySend(AuthenticateState.Success(User(user.email ?: "")))
            } else {
                trySend(AuthenticateState.Error(AuthenticatorException()))
            }
            close()
        }
        awaitClose()
    }

    override fun loginByEmailAndPassword(email: String, password: String) = callbackFlow {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = task.result.user
                trySend(AuthenticateState.Success(User(user?.email ?: "")))
            } else {
                trySend(AuthenticateState.Error(AuthenticatorException()))
            }
            close()
        }
        awaitClose()
    }
}
