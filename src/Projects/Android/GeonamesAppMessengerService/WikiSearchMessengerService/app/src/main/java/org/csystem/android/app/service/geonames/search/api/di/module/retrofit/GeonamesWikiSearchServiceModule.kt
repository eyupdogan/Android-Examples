/*--------------------------------------------------------------------------------------------------
    Bir service sınıfını binder yapan onBind metodunun override edilmesi ve aynı zamanda manifest e
    eklenen intent filter lar ile o sınıfın dışarıdan erişilebilir hale getirilmesidir.
--------------------------------------------------------------------------------------------------*/
package org.csystem.android.app.service.geonames.search.api.di.module.retrofit

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import org.csystem.android.app.service.geonames.search.api.IGeonamesWikiSearchService
import retrofit2.Retrofit

@Module
@InstallIn(ServiceComponent::class)
object GeonamesWikiSearchServiceModule
{
    @Provides
    fun create(retrofit: Retrofit):IGeonamesWikiSearchService
    {
        return retrofit.create(IGeonamesWikiSearchService::class.java)
    }
}