package com.example.graphql_dgs.course.datasource.problemz.repository

import com.example.graphql_dgs.course.datasource.problemz.entity.Problemz
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ProblemzRepository : JpaRepository<Problemz, UUID> {

    fun findAllByOrderByCreationTimestampDesc(): List<Problemz>?

    @Query(nativeQuery = true, value = "select * from problemz p where " +
            "upper(title) like upper(:keyword)" +
            " or upper(content) like upper(:keyword) " +
            "or upper(tags) like upper(:keyword)")
    fun findByKeyword(@Param("keyword") keyword: String?): List<Problemz>?

}