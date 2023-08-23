package com.clarkz.network.converter

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okio.Buffer
import retrofit2.Converter
import java.io.OutputStreamWriter
import java.nio.charset.StandardCharsets

class CustomGsonRequestBodyConverter<T> constructor(
    private val gson: Gson,
    private val adapter: TypeAdapter<T>
) : Converter<T, RequestBody> {
    private val mediaType = "application/json; charset=UTF-8".toMediaTypeOrNull()

    override fun convert(value: T): RequestBody {
        val buffer = Buffer()

        val writer = OutputStreamWriter(buffer.outputStream(), StandardCharsets.UTF_8)
        val jsonWriter = gson.newJsonWriter(writer)
        adapter.write(jsonWriter, value)
        jsonWriter.close()
        return buffer.readByteString()
            .toRequestBody(mediaType)//RequestBody.create(mediaType, buffer.readByteString())
    }


}