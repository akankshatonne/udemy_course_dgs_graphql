package com.example.graphql_dgs.course.datasource.fake

import com.netflix.dgs.codegen.generated.types.Address
import com.netflix.dgs.codegen.generated.types.Author
import com.netflix.dgs.codegen.generated.types.MobileApp
import com.netflix.dgs.codegen.generated.types.MobileAppCategory
import jakarta.annotation.PostConstruct
import net.datafaker.Faker
import org.springframework.context.annotation.Configuration
import java.net.URL
import java.time.LocalDate
import java.util.*
import java.util.concurrent.ThreadLocalRandom
import kotlin.collections.ArrayList


@Configuration
class FakeMobileAppDataSource(val faker: Faker) {
    companion object {
        val MOBILE_APP_LIST: MutableList<MobileApp> = ArrayList()
    }


    @PostConstruct
    private fun postConstruct() {
        for (i in 0..5) {
            val addresses: MutableList<Address> = ArrayList()
            val originCountry = faker.country().name()
            val authorName = faker.app().author()
            val author = Author(
                authorName,
                originCountry,
                addresses
            )
            val name = faker.app().name()
            val version = faker.app().version()
            val platform = randomMobileAppPlatform()
            val appId = UUID.randomUUID().toString()
            val releaseDate = LocalDate.now().minusDays(faker.random().nextLong(365))
            val downloaded = faker.number().numberBetween(1, 1_500_000)
//            val homepage = URL("https://" + faker.internet().url())
            val category = MobileAppCategory.entries[faker.random().nextInt(MobileAppCategory.entries.size)]
            val mobileApp = MobileApp(
                appId,
                name,
                version,
                platform,
                author,
                releaseDate,
                downloaded,
                category

//                releaseDate,
//                downloaded,
//                homepage,
//                category
            )

            for (j in 0 until ThreadLocalRandom.current().nextInt(1, 3)) {
                val country = faker.address().country()
                val city = faker.address().cityName()
                val street = faker.address().streetAddress()
                val zipCode = faker.address().zipCode()
                val address = Address(
                    country,
                    city,
                    street,
                    zipCode
                )

                addresses.add(address)
            }

            MOBILE_APP_LIST.add(mobileApp)
        }
    }

    private fun randomMobileAppPlatform(): List<String> {
        return when (ThreadLocalRandom.current().nextInt(10) % 3) {
            0 -> listOf("android")
            1 -> listOf("ios")
            else -> listOf("ios", "android")
        }
    }
}