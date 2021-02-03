package com.ayokunle.sanwoolu.data

import com.ayokunle.sanwoolu.models.UserEntity

interface UserRepository {

    suspend fun getUsersRemote(page: Int, limit: Int): List<UserEntity>

    suspend fun getUser(userId: String): UserEntity
}