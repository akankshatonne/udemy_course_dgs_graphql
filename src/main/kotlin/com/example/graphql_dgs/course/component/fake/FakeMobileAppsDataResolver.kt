package com.example.graphql_dgs.course.component.fake

import com.example.graphql_dgs.course.datasource.fake.FakeMobileAppDataSource
import com.netflix.dgs.codegen.generated.DgsConstants
import com.netflix.dgs.codegen.generated.types.MobileApp
import com.netflix.dgs.codegen.generated.types.MobileAppFilter
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument


@DgsComponent
class FakeMobileAppsDataResolver {

    @DgsQuery(field = DgsConstants.QUERY.MobileApps)
    fun getMobileApps ( @InputArgument(name = "mobileAppFilter") filter: MobileAppFilter?) : List<MobileApp>{
        filter?.let {
            return FakeMobileAppDataSource.MOBILE_APP_LIST.filter{ mobileApp -> matchFilter(filter, mobileApp)}
        }
        return FakeMobileAppDataSource.MOBILE_APP_LIST
}

    private fun matchFilter(mobileAppFilter: MobileAppFilter, mobileApp: MobileApp): Boolean{


        val isApp = mobileAppFilter.name == mobileApp.name
                && mobileAppFilter.version?.let{ mobileAppFilter.version == mobileApp.version } ?: true // if null continue the check


        if (!isApp) return false

        mobileAppFilter.platform?.let{
            mobileApp.platform?.let{
                if(!mobileApp.platform.containsAll(mobileAppFilter.platform)) return false
            }
        }

        mobileAppFilter.authorFilter?.let{
            mobileApp.author?.let{
               if( mobileApp.author.name != mobileAppFilter.authorFilter.name) return false
            }
        }

        mobileAppFilter.category?.let{
            mobileApp.category?.let{
                if(mobileApp.category != mobileAppFilter.category) return false
            }
        }

        return true

    }
}