package com.example.graphql_dgs.course.service.query

import com.example.graphql_dgs.course.datasource.problemz.entity.Userz
import com.example.graphql_dgs.course.datasource.problemz.repository.UserzRepository
import org.springframework.stereotype.Service


@Service
class UserzQueryService(val userzRepository: UserzRepository) {

    fun getUserByAuthToken(token: String?): Userz {
        return userzRepository.findUserByToken(token) ?: throw IllegalArgumentException("No user by token")
    }

}