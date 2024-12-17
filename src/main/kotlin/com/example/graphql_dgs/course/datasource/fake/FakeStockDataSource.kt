package com.example.graphql_dgs.course.datasource.fake

import com.netflix.dgs.codegen.generated.types.Stock
import net.datafaker.Faker
import org.springframework.context.annotation.Configuration
import java.time.OffsetDateTime

@Configuration
class FakeStockDataSource(val faker: Faker) {


    fun createRandomStock(): Stock {
        return Stock(faker.stock().nsdqSymbol(),faker.number().numberBetween(200,400), OffsetDateTime.now())
    }

}