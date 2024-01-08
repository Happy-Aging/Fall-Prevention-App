package com.appname.happyAging.domain.model.common

sealed class ApiResponse<out T>{
    class Success<out T>(val data: T) : ApiResponse<T>()
    class Error(val message: String) : ApiResponse<Nothing>()

    val isSuccess: Boolean get() = this is Success<T>
    val isError: Boolean get() = this is Error
}

inline fun <T> ApiResponse<T>.onSuccess(action: (T) -> Unit): ApiResponse<T> {
    if (this is ApiResponse.Success) action(data)
    return this
}

inline fun <T> ApiResponse<T>.onFailure(action: (String) -> Unit): ApiResponse<T> {
    if (this is ApiResponse.Error) action(message)
    return this
}