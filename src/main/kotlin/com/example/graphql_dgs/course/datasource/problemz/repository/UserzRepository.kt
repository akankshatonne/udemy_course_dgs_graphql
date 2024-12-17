package com.example.graphql_dgs.course.datasource.problemz.repository

import com.example.graphql_dgs.course.datasource.problemz.entity.Userz
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserzRepository : JpaRepository<Userz, UUID> {

    fun findByUsernameIgnoreCase(username: String): Userz?

    @Query(
        nativeQuery = true, value = ("select u.* "
                + "from userz u inner join userz_token ut "
                + "on u.id = ut.user_id "
                + "where ut.auth_token = ? "
                + " and ut.expiry_timestamp > current_timestamp")
    )
    fun findUserByToken(authToken: String?): Userz?
}