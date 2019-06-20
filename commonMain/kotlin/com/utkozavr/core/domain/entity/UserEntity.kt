package com.utkozavr.core.domain.entity

import kotlinx.serialization.Serializable
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

@Serializable
data class UserEntity(
    var id: Long? = null,
    var firstNName: String? = null,
    var lastName: String? = null,
    var email: String? = null,
    var password: String? = null

    ) {


    companion object {

        @UnstableDefault
        fun parseJsonString(str: String):UserEntity {
            return Json(JsonConfiguration(encodeDefaults = false)).parse(serializer(), str)
        }

    }


    @UnstableDefault
    fun toJsonString(): String {
        return Json.stringify(serializer(), this)
    }

}