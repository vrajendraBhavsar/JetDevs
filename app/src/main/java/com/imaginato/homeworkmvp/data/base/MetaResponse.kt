package com.imaginato.homeworkmvp.data.base

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
open class BaseResponse {
    @SerializedName("meta")
    lateinit var meta: MetaResponse
}

@Serializable
data class MetaResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String
)
