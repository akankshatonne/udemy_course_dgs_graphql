package com.example.graphql_dgs.course.service.query

import com.example.graphql_dgs.course.datasource.problemz.entity.Solutionz
import com.example.graphql_dgs.course.datasource.problemz.repository.SolutionzRepository
import org.springframework.stereotype.Service

@Service
class SolutionQueryService(val solutionzRepository: SolutionzRepository) {

    fun solutionzByKeyword( keyword: String?): List<Solutionz>? {
        return solutionzRepository.findByKeyword("%$keyword%")
    }

}