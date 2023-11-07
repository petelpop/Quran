package com.pall.quranapp.data

sealed class Resource<T>(val data:T? = null, val message:String? = null) {
    class Success<T>(val sesuatu: T?): Resource<T>()
    class Loading<T>(data: T? = null): Resource<T>()
    class Error<T>(message: String?, data: T? = null): Resource<T>(data, message)

}