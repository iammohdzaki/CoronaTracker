package com.zaki.coronatracker.model

/**
 * Developer : Mohammad Zaki
 * Created On : 01-04-2020
 */

sealed class CommonResponse<T> {
    data class Progress<T>(var loading: Boolean) : CommonResponse<T>()
    data class Success<T>(var data: T) : CommonResponse<T>()
    data class Failure<T>(val e: Throwable) : CommonResponse<T>()

    companion object {
        fun <T> loading(isLoading: Boolean): CommonResponse<T> =
            Progress(isLoading)

        fun <T> success(data: T): CommonResponse<T> =
            Success(data)

        fun <T> failure(e: Throwable): CommonResponse<T> =
            Failure(e)
    }
}