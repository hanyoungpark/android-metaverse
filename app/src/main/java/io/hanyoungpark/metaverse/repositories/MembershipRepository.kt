package io.hanyoungpark.metaverse.repositories

import io.hanyoungpark.metaverse.services.AuthenticateService
import io.hanyoungpark.metaverse.services.AuthenticateState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface MembershipRepository {
    fun fetchUser(): Flow<AuthenticateState>
    fun signUpEmailAndPassword(email: String, password: String): Flow<AuthenticateState>
    fun loginEmailAndPassword(email: String, password: String): Flow<AuthenticateState>
}

class MembershipRepositoryImpl @Inject constructor(
    private val authenticateService: AuthenticateService
) : MembershipRepository {
    override fun fetchUser(): Flow<AuthenticateState> {
        return authenticateService.fetchUser()
    }

    override fun signUpEmailAndPassword(email: String, password: String): Flow<AuthenticateState> {
        return authenticateService.signUpByEmailAndPassword(email, password)
    }

    override fun loginEmailAndPassword(email: String, password: String): Flow<AuthenticateState> {
        return authenticateService.loginByEmailAndPassword(email, password)
    }
}
