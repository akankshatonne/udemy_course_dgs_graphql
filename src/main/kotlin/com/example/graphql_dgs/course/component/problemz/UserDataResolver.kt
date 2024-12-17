package com.example.graphql_dgs.course.component.problemz

import com.example.graphql_dgs.course.datasource.problemz.util.GraphqlBeanMapper
import com.example.graphql_dgs.course.service.command.UserzMutationService
import com.example.graphql_dgs.course.service.query.UserzQueryService
import com.netflix.dgs.codegen.generated.DgsConstants
import com.netflix.dgs.codegen.generated.types.*
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.InputArgument
import org.springframework.web.bind.annotation.RequestHeader

@DgsComponent
class UserDataResolver(val userzMutationService: UserzMutationService, val userzQueryService: UserzQueryService) {

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.Me)
    fun accountInfo(@RequestHeader(name = "authToken", required = true) authToken: String?): User {
        return GraphqlBeanMapper.mapToGraphql(userzQueryService.getUserByAuthToken(authToken))
    }

    @DgsData(parentType = DgsConstants.Mutation_TYPE, field = DgsConstants.MUTATION.UserCreate)
    fun createUser(@InputArgument(name="user") userCreateInput: UserCreateInput): UserResponse {
      TODO()
    }

    @DgsData(parentType = DgsConstants.Mutation_TYPE, field = DgsConstants.MUTATION.UserLogin)
    fun loginUser(@InputArgument(name="user") userLoginInput: UserLoginInput): UserResponse {
        val authToken = userzMutationService.login(userLoginInput.username, userLoginInput.password)
        val userToken = GraphqlBeanMapper.mapToGraphql(authToken)
        val user = accountInfo(userToken.authToken)
        return UserResponse(user,userToken)
    }

    @DgsData(parentType = DgsConstants.Mutation_TYPE, field = DgsConstants.MUTATION.UserActivation)
    fun activateUser(@InputArgument(name="user") userActivationInput: UserActivationInput): UserActivationResponse {
        TODO()
    }

}