package com.example.graphql_dgs.course.datasource.fake

import com.netflix.dgs.codegen.generated.types.Address
import com.netflix.dgs.codegen.generated.types.Author
import com.netflix.dgs.codegen.generated.types.Book
import com.netflix.dgs.codegen.generated.types.ReleaseHistory
import jakarta.annotation.PostConstruct
import net.datafaker.Faker
import org.springframework.context.annotation.Configuration

@Configuration
class FakeBookDataSource(val faker: Faker) {

    companion object{
        val BOOK_LIST = arrayListOf<Book>()
    }

    @PostConstruct
    fun postConstruct(){
        for(i in 1..20)
        {
            var addresses = arrayListOf<Address>()
            for(i in 1..3)  addresses.add(Address(faker.address().streetName(), faker.address().city(),
                                                        faker.address().zipCode(), faker.address().country()))
            var author = Author(faker.book().author(),faker.country().name(),addresses)
            var releaseHistory = ReleaseHistory(faker.number().numberBetween(2012,2024),faker.bool().bool(),faker.country().name())
            var book = Book(faker.book().title(),faker.book().publisher(),author,releaseHistory)

            BOOK_LIST.add(book)

        }
    }
}