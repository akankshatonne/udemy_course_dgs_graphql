package com.example.graphql_dgs.course.datasource.problemz.util

import org.bouncycastle.crypto.generators.OpenBSDBCrypt

class HashUtil {

    companion object {
        fun isBcryptMatch(orignal: String?, hashvalue: String?): Boolean {
            return OpenBSDBCrypt.checkPassword(hashvalue, orignal?.toByteArray(charset("UTF-8")))
        }
    }
}