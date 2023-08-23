package com.clarkz.network.converter

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.ByteArrayInputStream
import java.io.InputStreamReader

class CustomGsonResponseBodyConverter<T> constructor(
    private val gson: Gson,
    private val adapter: TypeAdapter<T>
) : Converter<ResponseBody, T> {

    override fun convert(value: ResponseBody): T? {
        val response = value.string()

        val mediaType = value.contentType()
        val charset = mediaType?.charset(Charsets.UTF_8) ?: Charsets.UTF_8

        val arrayInput = ByteArrayInputStream(response.toByteArray(charset))

        val reader = InputStreamReader(arrayInput, charset)
        val jsonReader = gson.newJsonReader(reader)
        try {
            return adapter.read(jsonReader)
        } finally {
            reader.close()
            value.close()
        }
    }
}