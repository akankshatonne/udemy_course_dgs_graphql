package com.example.graphql_dgs.course.component.problemz

import com.netflix.dgs.codegen.generated.DgsConstants
import com.netflix.dgs.codegen.generated.types.Solution
import com.netflix.dgs.codegen.generated.types.SolutionCreateInput
import com.netflix.dgs.codegen.generated.types.SolutionResponse
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.InputArgument
import org.springframework.web.bind.annotation.RequestHeader
import reactor.core.publisher.Flux

@DgsComponent
class SolutionDataResolver {

    @DgsData(parentType = DgsConstants.Mutation_TYPE, field = DgsConstants.MUTATION.SolutionCreate)
    fun createSolution(
        @RequestHeader(name = "authToken", required = true) authToken: String,
        @InputArgument(name = "solution") solutionInput: SolutionCreateInput
    ): SolutionResponse {
        TODO()
    }


  @DgsData(parentType = DgsConstants.Mutation_TYPE, field = DgsConstants.MUTATION.SolutionVote)
    fun createSolutionVote(
      @RequestHeader(name = "authToken", required = true) authToken: String,
      @InputArgument(name = "vote") solutionInput: SolutionCreateInput
  ): SolutionResponse {
        TODO()
    }

    @DgsData(parentType = DgsConstants.Subscription_TYPE, field = DgsConstants.SUBSCRIPTION.SolutionVoteChanged)
    fun subscribeSolutionVote(@InputArgument(name = "solutionId") solutionId: String): Flux<Solution> {
        TODO()
    }

}