package com.example.judgeabookbyitscover.model.db

sealed class Result<out T : Any> {

    class Success<out T : Any>(val data: T) : Result<T>()

    class Error(val exception: Throwable, val message: String = exception.localizedMessage) : Result<Nothing>()
}