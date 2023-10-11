package com.clarkz.network.converter

import com.clarkz.baseapp.base.BaseServerRsp
import com.clarkz.network.exception.ApiException
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

    private val okCode = 200

    override fun convert(value: ResponseBody): T? {
        val response = value.string()

        val baseServerRsp = gson.fromJson(response, BaseServerRsp::class.java)

        if (baseServerRsp.code != okCode){
            value.close()
            throw  ApiException(baseServerRsp.code, baseServerRsp.msg)
        }

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