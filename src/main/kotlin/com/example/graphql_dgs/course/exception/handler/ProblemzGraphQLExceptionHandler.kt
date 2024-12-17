package com.example.graphql_dgs.course.exception.handler

import com.example.graphql_dgs.course.exception.ProblemzAuthenticationException
import com.netflix.graphql.dgs.exceptions.DefaultDataFetcherExceptionHandler
import com.netflix.graphql.types.errors.ErrorType
import com.netflix.graphql.types.errors.TypedGraphQLError
import graphql.execution.DataFetcherExceptionHandler
import graphql.execution.DataFetcherExceptionHandlerParameters
import graphql.execution.DataFetcherExceptionHandlerResult
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class ProblemzGraphQLExceptionHandler: DataFetcherExceptionHandler {
    private val defaultHandler = DefaultDataFetcherExceptionHandler()

    override fun handleException(handlerParameters: DataFetcherExceptionHandlerParameters): CompletableFuture<DataFetcherExceptionHandlerResult> {
        when (val exception = handlerParameters.exception) {
             is ProblemzAuthenticationException -> {
                 val graphQlError = TypedGraphQLError.newBuilder().message(exception.localizedMessage)
                     .path(handlerParameters.path)
                     .errorType(ErrorType.UNAUTHENTICATED)
                     .errorDetail(ProblemzErrorDetail())
                     .build()

                 val result = DataFetcherExceptionHandlerResult.newResult().error(graphQlError).build()
                 return CompletableFuture.completedFuture(result)
             }
        }
        return defaultHandler.handleException(handlerParameters)
    }
}