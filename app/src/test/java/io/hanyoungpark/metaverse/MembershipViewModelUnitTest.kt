package io.hanyoungpark.metaverse

import io.hanyoungpark.metaverse.models.User
import io.hanyoungpark.metaverse.repositories.MembershipRepository
import io.hanyoungpark.metaverse.services.AuthenticateState
import io.hanyoungpark.metaverse.viewmodels.MembershipViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.*
import org.junit.*
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class MembershipViewModelUnitTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @ExperimentalCoroutinesApi
    @Test
    fun `fetch user object when already logged on`() = runBlockingTest {
        val result = AuthenticateState.Success(User("test@test.com"))
        val flow = flow<AuthenticateState> {
            emit(result)
        }
        val membershipRepository = Mockito.mock(MembershipRepository::class.java)
        Mockito.`when`(membershipRepository.fetchUser()).thenReturn(flow)

        val membershipViewModel = MembershipViewModel(membershipRepository)
        membershipViewModel.fetchUser()
        Assert.assertEquals(result, flow.take(1).first())
        Mockito.verify(membershipRepository).fetchUser()
    }
}

