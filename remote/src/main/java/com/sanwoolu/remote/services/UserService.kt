package com.sanwoolu.remote.services

import com.sanwoolu.remote.models.BaseRemoteModel
import com.sanwoolu.remote.models.UserRemoteModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface UserService {

    @GET("user")
    suspend fun getUsers(@QueryMap data: HashMap<String, Int>): BaseRemoteModel<UserRemoteModel>

    @GET("user/{userId}")
    suspend fun getUser(@Path("userId") userId: String): UserRemoteModel
}