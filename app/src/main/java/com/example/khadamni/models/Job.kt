package com.example.khadamni.models

import java.util.*

data class Job(
    var _id: String? = null,
    var from: User? = null,
    var to: User? = null,
    var Price: Int? = null,
    var Description: String? = null

)
