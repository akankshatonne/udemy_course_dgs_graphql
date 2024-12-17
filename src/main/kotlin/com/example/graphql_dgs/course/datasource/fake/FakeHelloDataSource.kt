package com.example.graphql_dgs.course.datasource.fake

import com.netflix.dgs.codegen.generated.types.Hello
import jakarta.annotation.PostConstruct
import net.datafaker.Faker
import org.springframework.context.annotation.Configuration

@Configuration
class FakeHelloDataSource(val faker: Faker) {

    companion object{
        val HELLO_LIST = mutableListOf<Hello>()
    }

    @PostConstruct
    fun postConstruct(){
        for (i in 1..10) {
            HELLO_LIST.add(Hello(faker.company().name(),faker.random().nextInt(5000)))
        }
    }

}