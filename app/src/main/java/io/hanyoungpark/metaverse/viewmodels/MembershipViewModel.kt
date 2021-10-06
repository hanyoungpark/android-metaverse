package io.hanyoungpark.metaverse.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.hanyoungpark.metaverse.models.User
import io.hanyoungpark.metaverse.repositories.MembershipRepository
import io.hanyoungpark.metaverse.services.AuthenticateState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MembershipViewModel @Inject constructor(
    private val membershipRepository: MembershipRepository
): ViewModel() {

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> = _user

    fun fetchUser() = viewModelScope.launch {
        membershipRepository.fetchUser().collect {
            when (it) {
                is AuthenticateState.Success -> _user.postValue(it.user)
                is AuthenticateState.Error -> _user.postValue(null)
            }
        }
    }

    fun loginEmailAndPassword(email: String, password: String) = viewModelScope.launch {
        Log.d("ViewModel", "login $email, $password")
        membershipRepository.loginEmailAndPassword(email, password).collect {
            when (it) {
                is AuthenticateState.Success -> _user.postValue(it.user)
                is AuthenticateState.Error -> _user.postValue(null)
            }
        }
    }
}
