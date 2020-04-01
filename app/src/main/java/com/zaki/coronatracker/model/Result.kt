package com.zaki.coronatracker.model

open class Result<T>(val data: T? = null, val error: Throwable? = null) {

    companion object {
        fun <T> fromData(data: T): Result<T> {
            return Result(data, null)
        }

        fun <T> fromError(error: Throwable): Result<T> {
            return Result(null, error)
        }
    }
}