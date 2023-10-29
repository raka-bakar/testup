package com.sumup.challenge.toastcatalog

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class ZonedDateTimeAdapter : JsonAdapter<ZonedDateTime>() {
    companion object {
        private val FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    }

    override fun toJson(writer: JsonWriter, value: ZonedDateTime?) {
        if (value == null) {
            writer.nullValue()
        } else {
            val string = FORMATTER.format(value)
            writer.value(string)
        }
    }

    override fun fromJson(reader: JsonReader): ZonedDateTime? {
        if (reader.peek() == JsonReader.Token.NULL) {
            return reader.nextNull<ZonedDateTime>()
        }
        val string = reader.nextString()
        val temp = try {
            FORMATTER.parse(string)
        }catch (e : DateTimeParseException){
            reader.nextNull<LocalDateTime>()
        }
        return ZonedDateTime.from(temp)
    }
}