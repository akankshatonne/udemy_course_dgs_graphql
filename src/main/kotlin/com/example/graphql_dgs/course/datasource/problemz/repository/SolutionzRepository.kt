package com.example.graphql_dgs.course.datasource.problemz.repository

import com.example.graphql_dgs.course.datasource.problemz.entity.Solutionz
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface SolutionzRepository : JpaRepository<Solutionz, Int> {

    @Query(nativeQuery = true, value = "select * from solutionz where upper(content) like upper(:keyword)")
    fun findByKeyword(@Param("keyword") keyword: String?): List<Solutionz>?
}