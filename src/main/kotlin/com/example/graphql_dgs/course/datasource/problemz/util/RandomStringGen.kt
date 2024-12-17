package com.example.graphql_dgs.course.datasource.problemz.util

class RandomStringGen {
    companion object {
        val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        fun randomStringByKotlinCollectionRandom(stringLength: Int) =
            List(stringLength) { charPool.random() }.joinToString("")
    }

}