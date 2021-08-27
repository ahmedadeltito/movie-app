package com.talentsarena.core.api

/**
 * Separate class for error responses so that we can make ApiErrorResponse's has a [errorMessage].
 */
data class ApiErrorResponse<T>(val errorMessage: String) : ApiResponse<T>()