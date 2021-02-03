package com.ayokunle.sanwoolu.models

import com.ayokunle.sanwoolu.utils.safeString
import com.sanwoolu.remote.models.LocationRemoteModel

data class LocationEntity(
    val street: String,
    val city: String,
    val state: String,
    val country: String,
    val timezone: String
) {

    companion object {
        @JvmStatic
        fun fromRemote(model: LocationRemoteModel?): LocationEntity {
            return LocationEntity(
                street = safeString(model?.street),
                city = safeString(model?.city),
                state = safeString(model?.state),
                country = safeString(model?.country),
                timezone = safeString(model?.timezone)
            )
        }
    }
}