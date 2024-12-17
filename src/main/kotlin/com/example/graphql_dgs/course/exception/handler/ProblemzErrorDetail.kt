package com.example.graphql_dgs.course.exception.handler

import com.netflix.graphql.types.errors.ErrorDetail
import com.netflix.graphql.types.errors.ErrorType

class ProblemzErrorDetail: ErrorDetail {
    override fun getErrorType(): ErrorType {
        return ErrorType.UNAUTHENTICATED
    }

    override fun toString(): String {
        return "User Validation Failed: Check Username and Password!"
    }
}