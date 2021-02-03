package com.ayokunle.sanwoolu.presentation.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayokunle.sanwoolu.data.UserRepository
import com.ayokunle.sanwoolu.models.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.properties.Delegates

@HiltViewModel
class UsersFragmentViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _userResponse = MutableStateFlow<GetUserResponse>(GetUserResponse.Initial)
    val userResponse: StateFlow<GetUserResponse> get() = _userResponse.asStateFlow()

    var currentPage: Int by Delegates.observable(0) { _, _, newValue ->
        getUsers()
        if (newValue <= 1) {
            _userResponse.value = GetUserResponse.Loading
        } else {
            _userResponse.value = GetUserResponse.LoadingMore
        }
    }
    private val limit = 15

    private fun getUsers() {
        viewModelScope.launch {
            val response = runCatching { repository.getUsersRemote(currentPage, limit) }
            response.onSuccess {
                _userResponse.value = GetUserResponse.Successful(it)
                val users = it.map {
                    async {
                        repository.getUser(it.id)
                    }
                }.awaitAll()
                _userResponse.value = GetUserResponse.Successful(users)
            }
            response.onFailure {
                _userResponse.value =
                    GetUserResponse.Failed(it.localizedMessage ?: "An error occurred")
            }
        }
    }

    sealed class GetUserResponse {
        object Initial : GetUserResponse()
        object Loading : GetUserResponse()
        object LoadingMore : GetUserResponse()
        data class Failed(val message: String) : GetUserResponse()
        data class Successful(val data: List<UserEntity>) : GetUserResponse()
    }
}