package com.sumup.challenge.toastcatalog.model

import com.squareup.moshi.JsonClass
import java.time.ZonedDateTime

@JsonClass(generateAdapter = true)
class Item(
    val name: String,
    val price: String,
    val id: Int,
    val time : ZonedDateTime,
    val currency : String
)
