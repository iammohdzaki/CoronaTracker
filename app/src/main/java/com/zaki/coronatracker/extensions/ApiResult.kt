package com.zaki.coronatracker.extensions

/**
 * Developer : Mohammad Zaki
 * Created On : 01-04-2020
 */
data class APIResult<out T>(val status: Status, val data: T?, val message: String?, val code:Int? = null) {
    companion object {
        fun <T> success(data: T?, message: String? = null): APIResult<T> {
            return APIResult(Status.SUCCESS, data, message)
        }

        fun <T> error(msg: String?, code: Int? = null): APIResult<T> {
            return APIResult(Status.ERROR, null, msg, code)
        }

        fun <T> loading(data: T? = null): APIResult<T> {
            return APIResult(Status.LOADING, data, null)
        }
    }
}


enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}