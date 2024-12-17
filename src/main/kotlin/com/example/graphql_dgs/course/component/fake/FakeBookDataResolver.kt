package com.example.graphql_dgs.course.component.fake

import com.example.graphql_dgs.course.datasource.fake.FakeBookDataSource
import com.netflix.dgs.codegen.generated.DgsConstants
import com.netflix.dgs.codegen.generated.types.Book
import com.netflix.dgs.codegen.generated.types.ReleaseHistoryInput
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import graphql.schema.DataFetchingEnvironment
import io.micrometer.common.util.StringUtils

@DgsComponent
class FakeBookDataResolver {

    @DgsQuery(field = "books")
    fun booksWrittenBy (@InputArgument(name="author") authorName: String?): List<Book> {
            if (authorName == null || StringUtils.isBlank(authorName)) return  FakeBookDataSource.BOOK_LIST
        else
            return FakeBookDataSource.BOOK_LIST.filter{it.author.name.equals(authorName, ignoreCase = true)}
    }

    @DgsQuery(field = "booksByReleaseHistory")
    fun getbooksByReleaseHistory(dataFetchingEnvironment: DataFetchingEnvironment): List<Book> {
        var releaseHistory = dataFetchingEnvironment.getArgument<Map<String, Any>>("releasedInput")
        val releasedInput: ReleaseHistoryInput = ReleaseHistoryInput(releaseHistory?.get(DgsConstants.RELEASEHISTORYINPUT.Year) as Int? ,releaseHistory?.get(DgsConstants.RELEASEHISTORYINPUT.PrintedEdition) as Boolean?)

        return FakeBookDataSource.BOOK_LIST.filter{
            it.releaseHistory?.year == releasedInput.year && it.releaseHistory?.printedEdition == releasedInput.printedEdition
        }

    }
}