package com.example.graphql_dgs.course.datasource.problemz.repository

import com.example.graphql_dgs.course.datasource.problemz.entity.UserzToken
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserzTokenRepository: JpaRepository<UserzToken, UUID> {



}