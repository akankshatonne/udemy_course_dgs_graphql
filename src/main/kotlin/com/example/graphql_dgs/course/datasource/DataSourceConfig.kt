package com.example.graphql_dgs.course.datasource

import org.springframework.context.annotation.Configuration
import net.datafaker.Faker
import org.springframework.context.annotation.Bean

@Configuration
class DataSourceConfig() {

    @Bean
    fun faker(): Faker = Faker()

}