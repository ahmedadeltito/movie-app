package com.talentsarena.core.api

/**
 * Separate class for HTTP 200 responses so that we can make ApiSuccessResponse's body.
 */
data class ApiSuccessResponse<T>(val body: T) : ApiResponse<T>()