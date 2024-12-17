package com.example.graphql_dgs.course.service.command

import com.example.graphql_dgs.course.datasource.problemz.entity.UserzToken
import com.example.graphql_dgs.course.datasource.problemz.repository.UserzRepository
import com.example.graphql_dgs.course.datasource.problemz.repository.UserzTokenRepository
import com.example.graphql_dgs.course.datasource.problemz.util.HashUtil
import com.example.graphql_dgs.course.datasource.problemz.util.RandomStringGen
import com.example.graphql_dgs.course.exception.ProblemzAuthenticationException
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class UserzMutationService(val userzRepository: UserzRepository, val userzTokenRepository: UserzTokenRepository) {

    fun login(username: String, password: String): UserzToken {
        val userResult = userzRepository.findByUsernameIgnoreCase(username)

        if (userResult == null || !HashUtil.isBcryptMatch(password, userResult.hashedPassword)) throw ProblemzAuthenticationException("invalid credentials")

        val authToken = RandomStringGen.randomStringByKotlinCollectionRandom(32)

        return refreshAuthToken(userResult.id,authToken)


    }

    private fun refreshAuthToken(userID: UUID, authToken: String ): UserzToken{
        val userToken = UserzToken()
        userToken.userId = userID
        userToken.authToken = authToken
        val now = LocalDateTime.now()
        userToken.creationTimestamp = now
        userToken.expiryTimestamp = now.plusHours(2)

        return userzTokenRepository.save(userToken)
    }


}