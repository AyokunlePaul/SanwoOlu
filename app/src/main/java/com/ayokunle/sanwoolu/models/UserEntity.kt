package com.ayokunle.sanwoolu.models

import com.ayokunle.sanwoolu.utils.safeString
import com.sanwoolu.remote.models.UserRemoteModel

data class UserEntity(
    val id: String,
    val title: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val email: String,
    val dateOfBirth: String,
    val registerDate: String,
    val phone: String,
    val picture: String,
    val location: LocationEntity
) {

    companion object {
        @JvmStatic
        fun fromRemote(model: UserRemoteModel): UserEntity {
            return UserEntity(
                id = safeString(model.id),
                title = safeString(model.title),
                firstName = safeString(model.firstName),
                lastName = safeString(model.lastName),
                gender = safeString(model.gender),
                email = safeString(model.email),
                dateOfBirth = safeString(model.dateOfBirth),
                registerDate = safeString(model.registerDate),
                phone = safeString(model.phone),
                picture = safeString(model.picture),
                location = LocationEntity.fromRemote(model.location)
            )
        }
    }
}