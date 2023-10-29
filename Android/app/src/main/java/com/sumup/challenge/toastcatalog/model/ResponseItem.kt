package com.sumup.challenge.toastcatalog.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.sumup.challenge.toastcatalog.data.CurrencyS
import java.time.ZonedDateTime

@JsonClass(generateAdapter = true)
data class ResponseItem(
	val price: String,
	val name: String,
	@CurrencyS
	val currency: String,
	val id: Int,
	@Json(name = "last_sold")
	val lastSold: ZonedDateTime
)
