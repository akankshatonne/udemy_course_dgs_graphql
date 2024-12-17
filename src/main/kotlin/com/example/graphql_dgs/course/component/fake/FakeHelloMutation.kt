package com.example.graphql_dgs.course.component.fake

import com.example.graphql_dgs.course.datasource.fake.FakeBookDataSource
import com.example.graphql_dgs.course.datasource.fake.FakeHelloDataSource
import com.netflix.dgs.codegen.generated.DgsConstants
import com.netflix.dgs.codegen.generated.types.*
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.DgsRuntimeWiring
import com.netflix.graphql.dgs.InputArgument
import graphql.schema.idl.RuntimeWiring
import org.springframework.graphql.execution.RuntimeWiringConfigurer


@DgsComponent
class FakeHelloMutation {

    @DgsMutation
    fun addHello(@InputArgument(name = "helloInput") helloInput: HelloInput): Int {
        FakeHelloDataSource.HELLO_LIST.add(Hello(helloInput.text,helloInput.number))
        return FakeHelloDataSource.HELLO_LIST.size
    }

//    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.ChangeHelloText)
//    fun changeHelloText(@InputArgument helloInput: HelloInput): List<Hello> {
//        FakeHelloDataSource.HELLO_LIST.stream().filter{it.randomNumber == helloInput.number}.forEach{it.text = helloInput.text}
//
//        return FakeHelloDataSource.HELLO_LIST
//    }
}