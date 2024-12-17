package com.example.graphql_dgs.course.component.problemz

import com.example.graphql_dgs.course.datasource.problemz.entity.Problemz
import com.example.graphql_dgs.course.datasource.problemz.util.GraphqlBeanMapper
import com.example.graphql_dgs.course.service.query.ProblemzQueryService
import com.netflix.dgs.codegen.generated.DgsConstants
import com.netflix.dgs.codegen.generated.types.Problem
import com.netflix.dgs.codegen.generated.types.ProblemCreateInput
import com.netflix.dgs.codegen.generated.types.ProblemResponse
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.InputArgument
import java.util.*

@DgsComponent
class ProblemDataResolver(val problemzQueryService: ProblemzQueryService) {


    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.ProblemLatestList)
    fun queryLatestProblems(): List<Problem>? {
        return problemzQueryService.getLatestProblemz()?.map{GraphqlBeanMapper.mapToGraphql(it)}
    }

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.ProblemDetail)
    fun getProblemDetail(@InputArgument(name = "id") problemId: String): Problem {
        val uuid = UUID.fromString(problemId)
        return GraphqlBeanMapper.mapToGraphql(problemzQueryService.getProblemzById(uuid))
    }

    @DgsData(parentType = DgsConstants.Mutation_TYPE, field = DgsConstants.MUTATION.ProblemCreate)
    fun createProblem(@InputArgument(name = "problem") problem: ProblemCreateInput): ProblemResponse {
        TODO()
    }


}