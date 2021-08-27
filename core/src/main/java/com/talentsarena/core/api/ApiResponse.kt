package com.talentsarena.core.api

import retrofit2.Response

/**
 * Common class used by API responses.
 *
 * @param <T> the type of the response object
 */
sealed class ApiResponse<T> {
    companion object {
        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(body = body)
                }
            } else {
                if (response.message().isEmpty()) {
                    ApiErrorResponse(response.errorBody()?.string() ?: "Something went wrong!")
                } else {
                    ApiErrorResponse(response.message())
                }
            }
        }
    }
}