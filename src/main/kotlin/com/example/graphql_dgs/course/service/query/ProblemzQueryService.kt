package com.example.graphql_dgs.course.service.query

import com.example.graphql_dgs.course.datasource.problemz.entity.Problemz
import com.example.graphql_dgs.course.datasource.problemz.repository.ProblemzRepository
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ProblemzQueryService(val problemzRepository: ProblemzRepository) {

    fun getLatestProblemz(): List<Problemz>? {
        return problemzRepository.findAllByOrderByCreationTimestampDesc()
    }

    fun getProblemzById(id: UUID): Problemz {
        return problemzRepository.findById(id).orElseThrow { DgsEntityNotFoundException("Problemz with id $id not found") }
    }

    fun problemzByKeyword( keyword: String?): List<Problemz>? {
        return problemzRepository.findByKeyword("%$keyword%")
    }


}