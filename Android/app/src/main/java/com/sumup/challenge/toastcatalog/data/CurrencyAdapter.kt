package com.sumup.challenge.toastcatalog.data

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson

@JsonQualifier
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FUNCTION)
internal annotation class CurrencyS


internal class CurrencyAdapter : JsonAdapter<String>() {
    @ToJson
    override fun toJson(writer: JsonWriter, @CurrencyS str: String?) {
        if (str == "€") {
            writer.value("EUR")
        }
    }

    @FromJson
    @CurrencyS
    override fun fromJson(reader: JsonReader): String {
        val currency = reader.nextString()
        return if (currency == "EUR") {
            "€"
        } else {
            ""
        }
    }
}