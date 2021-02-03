package com.ayokunle.sanwoolu.data.remote

import com.ayokunle.sanwoolu.data.UserRepository
import com.ayokunle.sanwoolu.models.UserEntity
import com.ayokunle.sanwoolu.utils.dispatcher.SanwoOluDispatcher
import com.sanwoolu.remote.services.UserService
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val service: UserService,
    private val dispatcher: SanwoOluDispatcher
) : UserRepository {

    override suspend fun getUsersRemote(page: Int, limit: Int): List<UserEntity> =
        withContext(dispatcher.io) {
            val data = hashMapOf("page" to page, "limit" to limit)
            val result = service.getUsers(data)
            result.data.map { UserEntity.fromRemote(it) }
        }

    override suspend fun getUser(userId: String): UserEntity = withContext(dispatcher.io) {
        UserEntity.fromRemote(service.getUser(userId))
    }
}