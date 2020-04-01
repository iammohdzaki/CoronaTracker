package com.zaki.coronatracker.extensions

import android.annotation.SuppressLint
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.liveData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.zaki.coronatracker.R
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.File
import java.io.IOException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException

/**
 * Developer : Mohammad Zaki
 * Created On : 01-04-2020
 */

const val unspecified_error = "Unspecified error"


fun String?.jsonArrayOf(field: String): JSONArray =
    JSONObject(this ?: "{}").optJSONArray(field) ?: JSONArray()


val String.toGoogleDocUrl
    get() = "http://docs.google.com/gview?embedded=true&url=$this"


fun ImageView.loadImage(
    url: String?,
    //(TODO) Add Place holder
    placeholderRes: Int = 0,
    errorRes: Int = placeholderRes
) {
    kotlin.runCatching {
        Glide.with(context)
            .setDefaultRequestOptions(RequestOptions().placeholder(placeholderRes).error(errorRes))
            .load(url)
            .thumbnail(0.7f)
            .into(this)
    }
}


inline fun String?.toRequestBody(): RequestBody {
    return (this ?: "").toRequestBody("text/plain".toMediaTypeOrNull())
}

inline fun File.toMultipartBody(key: String): MultipartBody.Part {
    val requestBody =
        this.asRequestBody("multipart/form-data".toMediaTypeOrNull())

    return MultipartBody.Part.createFormData(
        key,
        name,
        requestBody
    )
}


fun <T> Call<T>.onCall(onResponse: (networkException: Throwable?, response: Response<T>?) -> Unit) {
    this.enqueue(object : Callback<T> {
        override fun onFailure(call: Call<T>, t: Throwable) {
            onResponse.invoke(t, null)
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            onResponse.invoke(null, response)
        }

    })
}

val String?.hasError
    get() = JSONObject(this ?: "{}").optBoolean("error", false)


val String?.errorMessage: String
    get() = JSONObject(this?.takeUnless { it.isEmpty() } ?: "{}").run {
        listOf(
            "message",
            "msg",
            "errorMsg",
            "errorMessage"
        ).find { has(it) }?.run { getString(this) } ?: unspecified_error
    }

val String?.successMessage: String
    get() = JSONObject(this?.takeUnless { it.isEmpty() } ?: "{}").run {
        listOf(
            "message",
            "msg",
            "successMsg",
            "successMessage"
        ).find { has(it) }?.run { getString(this) } ?: "Success"
    }


fun responseBodyLiveData(call: suspend () -> Response<ResponseBody>) = liveData {
    try {
        emit(APIResult.loading(null))
        val response = safeAPICall { call() }.string()

        if (response.hasError) emit(APIResult.error(response.errorMessage))
        else emit(APIResult.success(response, response.successMessage))
    } catch (e: Exception) {
        e.printStackTrace()
        val error = ResponseError(e.message ?: "Something went wrong. Please try again", 500)
        emit(APIResult.error(error.message, error.errorCode))
    }
}


@SuppressLint("LogNotTimber")
suspend fun <T : Any> safeAPICall(call: suspend () -> Response<T>): T {
    val response = try {
        call.invoke()
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
        val message = if (e is ConnectException || e is SocketTimeoutException ||
            e is HttpException ||
            e.message?.contains("unexpected end of stream on Connection") == true ||
            e is SocketException
        )
            "Connection Error" else unspecified_error
        val responseError = ResponseError(message, 500).convertToJsonString()
        Log.e("safeAPICall", "safeAPICall: error thrown = $responseError")
        Log.e("safeAPICall", "safeAPICall: actual error = ${e.message}", e)

        //json is passed as message
        throw IOException(responseError)
    }



    if (response.isSuccessful) {
        return response.body()!!
    } else {
        val error = response.errorBody()?.string()

        error?.let {
            val message = JSONObject(it).optString("message", "Something went wrong")
            val responseError = ResponseError(
                message.takeIf { response.code() != 500 } ?: response.message(),
                response.code()
            )
            throw IOException(responseError.convertToJsonString())
        }
        throw IOException(
            ResponseError(
                "Something went wrong. Please try again.",
                500
            ).convertToJsonString()
        )
    }
}

@SuppressLint("LogNotTimber")
inline fun Any.convertToJsonString(): String {
    return try {
        Gson().toJson(this) ?: ""
    } catch (e: Exception) {
        e.printStackTrace()
        Log.e("Extensions", "convertToJsonString: ${e.message}")
        "{}"
    }
}

data class ResponseError(val message: String, val errorCode: Int)

