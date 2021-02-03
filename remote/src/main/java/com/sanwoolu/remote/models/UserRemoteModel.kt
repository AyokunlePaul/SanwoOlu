package com.sanwoolu.remote.models

data class UserRemoteModel(
    val id: String?,
    val title: String?,
    val firstName: String?,
    val lastName: String?,
    val gender: String?,
    val email: String?,
    val dateOfBirth: String?,
    val registerDate: String?,
    val phone: String?,
    val picture: String?,
    val location: LocationRemoteModel?
)