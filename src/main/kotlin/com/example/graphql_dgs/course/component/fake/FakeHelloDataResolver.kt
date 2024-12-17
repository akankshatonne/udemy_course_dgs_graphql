package com.example.graphql_dgs.course.component.fake

import com.example.graphql_dgs.course.datasource.fake.FakeHelloDataSource
import com.netflix.dgs.codegen.generated.types.Hello
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery

@DgsComponent
class FakeHelloDataResolver {

    @DgsQuery
    fun allHellos(): List<Hello>{
        return FakeHelloDataSource.HELLO_LIST
    }
}