package com.maha.nasatest.data.remote

 sealed class UseCaseResult <out T: Any>{
    class OnSuccess<out T : Any>(val data: T) : UseCaseResult<T>()
    class OnError<U>(val exception: Throwable) : UseCaseResult<Nothing>()
}