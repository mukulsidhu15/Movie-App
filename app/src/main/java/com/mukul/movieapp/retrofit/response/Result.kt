package com.mukul.movieapp.retrofit.response

typealias RootError = Error

sealed interface Result<out D, out E : RootError> {
    class Loading<out D, out E : RootError> : Result<D, E>
    data class Success<out D, out E : RootError>(val data: D) : Result<D, E>
    data class Error<out D, out E : RootError>(val error: E) : Result<D, E>
}