package com.imaginato.homeworkmvp.data.base

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

class ApiListResponse<T> : BaseResponse() {
    @SerializedName("data")
    var data: List<T>? = null
}

@Serializable
open class ApiResponse<T> : BaseResponse() {
    @SerializedName("data")
    var data: T? = null
}
