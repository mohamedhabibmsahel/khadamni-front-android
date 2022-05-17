package com.example.khadamni.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class Job(
    @SerializedName("_id")
    var _id: String? = null,
    @SerializedName("from")
    var from: String? = null,
    @SerializedName("to")
    var to: String? = null,
    @SerializedName("price")
    var Price: Int? = null,
    @SerializedName("description")
    var Description: String? = null,

    @SerializedName("accepted")
var accepted: Boolean? = null

)
