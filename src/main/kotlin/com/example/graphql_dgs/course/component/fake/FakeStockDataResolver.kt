package com.example.graphql_dgs.course.component.fake

import com.example.graphql_dgs.course.datasource.fake.FakeStockDataSource
import com.netflix.dgs.codegen.generated.DgsConstants
import com.netflix.dgs.codegen.generated.types.Stock
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Autowired
import reactor.core.publisher.Flux
import java.time.Duration

@DgsComponent
class FakeStockDataResolver(@Autowired val fakeStockDataSource: FakeStockDataSource) {

    @DgsData(parentType = DgsConstants.SUBSCRIPTION.TYPE_NAME, field = DgsConstants.SUBSCRIPTION.RandomStock)
    fun getRandomStock(): Publisher<Stock>{
        return Flux.interval(Duration.ofSeconds(3)).map { t -> fakeStockDataSource.createRandomStock() }
    }


}