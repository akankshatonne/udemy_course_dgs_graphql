package com.example.graphql_dgs.course.config

import graphql.scalars.ExtendedScalars
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.graphql.execution.RuntimeWiringConfigurer

@Configuration
class GraphQLConfig {
    @Bean
    fun dateConfigurer(): RuntimeWiringConfigurer = RuntimeWiringConfigurer { wiringBuilder ->
        wiringBuilder.scalar(ExtendedScalars.Date)
    }

    @Bean
    fun nonNegativeIntConfigurer(): RuntimeWiringConfigurer = RuntimeWiringConfigurer { wiringBuilder ->
        wiringBuilder.scalar(ExtendedScalars.PositiveFloat)
    }

    @Bean
    fun urlConfigurer(): RuntimeWiringConfigurer = RuntimeWiringConfigurer { wiringBuilder ->
        wiringBuilder.scalar(ExtendedScalars.Url)
    }
}