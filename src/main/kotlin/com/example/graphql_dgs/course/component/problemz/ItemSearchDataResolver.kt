package com.example.graphql_dgs.course.component.problemz

import com.example.graphql_dgs.course.datasource.problemz.repository.ProblemzRepository
import com.example.graphql_dgs.course.datasource.problemz.repository.SolutionzRepository
import com.example.graphql_dgs.course.datasource.problemz.util.GraphqlBeanMapper
import com.example.graphql_dgs.course.service.query.ProblemzQueryService
import com.example.graphql_dgs.course.service.query.SolutionQueryService
import com.netflix.dgs.codegen.generated.DgsConstants
import com.netflix.dgs.codegen.generated.types.SearchItemFilter
import com.netflix.dgs.codegen.generated.types.SearchableItem
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.InputArgument
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException

@DgsComponent
class ItemSearchDataResolver(val problemzQueryService: ProblemzQueryService, val solutionQueryService: SolutionQueryService) {



    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.ItemSearch)
    fun searchItems(@InputArgument(name = "filter") filter: SearchItemFilter?): List<SearchableItem>{

        val resultList = mutableListOf<SearchableItem>()

        val problemzMatch = problemzQueryService.problemzByKeyword(filter?.keyword)
        val solutionzMatch = solutionQueryService.solutionzByKeyword(filter?.keyword)

        problemzMatch?.let{
            problemzMatch.forEach{resultList.add(GraphqlBeanMapper.mapToGraphql(it))}
        }
        solutionzMatch?.let{
            solutionzMatch.forEach{resultList.add(GraphqlBeanMapper.mapToGraphql(it))}
        }

        if(resultList.isEmpty()){
            throw DgsEntityNotFoundException("No item with keyword '${filter?.keyword}' found")
        }
        return resultList

    }


}