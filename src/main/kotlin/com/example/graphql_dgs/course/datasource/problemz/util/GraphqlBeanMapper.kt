package com.example.graphql_dgs.course.datasource.problemz.util

import com.example.graphql_dgs.course.datasource.problemz.entity.Problemz
import com.example.graphql_dgs.course.datasource.problemz.entity.Solutionz
import com.example.graphql_dgs.course.datasource.problemz.entity.Userz
import com.example.graphql_dgs.course.datasource.problemz.entity.UserzToken
import com.netflix.dgs.codegen.generated.types.*
import org.ocpsoft.prettytime.PrettyTime
import java.time.ZoneOffset

class GraphqlBeanMapper {

    companion object{
        val PRETTY_TIME: PrettyTime = PrettyTime()
        val ZONE_OFFSET: ZoneOffset = ZoneOffset.ofHours(7)

        fun mapToGraphql(orignal: Userz): User {
            var createDateTime = orignal.creationTimestamp.atOffset(ZONE_OFFSET)

            return User(orignal.id.toString(),
                        orignal.username,
                        orignal.email,
                        orignal.avatar,
                        createDateTime,
                        orignal.displayName)
        }

        fun mapToGraphql(orignal: Solutionz): Solution {
            var createDateTime = orignal.creationTimestamp.atOffset(ZONE_OFFSET)
            var author = mapToGraphql(orignal.createdBy)
            var category = if (orignal.category.equals(SolutionCategory.EXPLANATION.toString(), true)) SolutionCategory.EXPLANATION else SolutionCategory.REFERENCE

            return Solution(orignal.id.toString(),
                            createDateTime,
                            PRETTY_TIME.format(createDateTime),
                            orignal.content,
                            category,
                            orignal.voteGoodCount,
                            orignal.voteBadCount,
                            author
                            )

        }

        fun mapToGraphql(orignal: Problemz): Problem {
            var createDateTime = orignal.creationTimestamp.atOffset(ZONE_OFFSET)
            var created_by = mapToGraphql(orignal.createdBy)
            var solutions = orignal.solutions?.map { mapToGraphql(it) }
            var tagList = orignal.tags.split(",")

            return Problem(orignal.id.toString(),
                            createDateTime,
                            PRETTY_TIME.format(createDateTime),
                            orignal.title,
                            orignal.content,
                            tagList,
                            solutions?.size ?: 0,
                            created_by,
                            solutions
                            )
        }
        fun mapToGraphql(orignal: UserzToken): UserAuthToken {
            var expiryDate = orignal.expiryTimestamp?.atOffset(ZONE_OFFSET)

            return UserAuthToken(orignal.authToken,
                                expiryDate)

        }

    }

}