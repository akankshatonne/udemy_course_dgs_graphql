package com.example.graphql_dgs.course

import com.netflix.graphql.dgs.DgsQueryExecutor
import net.datafaker.Faker
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test

@SpringBootTest
class FakeBookDataResolverTest(val dgsQueryExecutor: DgsQueryExecutor, val faker: Faker) {

    @Test
    fun testAllBooks(){
    }

}