package org.csystem.android.app.org_csystem_android_app_retrofit.di.module

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//mvn install:install-file -DgroupId=org.csystem.android -DartifactId=org-csystem-android-datetime -Dversion=20.0.0 -Dfile=../jars/org-csystem-android-util-datetime-release-20.0.0.aar -Dpackaging=aar -DgeneratePom=true -DlocalRepositoryPath=. -DcreateChecksum=true
//mvn install:install-file -DgroupId=org.csystem.android -DartifactId=org-csystem-android-datetime -Dversion=20.0.0 -Dfile=../jars/org-csystem-android-util-datetime-release-20.0.0.aar -Dpackaging=aar -DgeneratePom=true -DlocalRepositoryPath=. -DcreateChecksum=true
//mvn install:install-file -DgroupId=com.dogandev -DartifactId=com-dogandev-util-console -Dversion=11.0.0 -Dfile=../jars/com-dogandev-util-console-11.0.0.jar -Dpackaging=jar -DgeneratePom=true -DlocalRepositoryPath=. -DcreateChecksum=true



@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule
{
    @Provides
    @Singleton
    fun create(baseUrl:String):Retrofit
    {
        return Retrofit.Builder().run {
            baseUrl(baseUrl)
            addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            client(OkHttpClient.Builder().build())
            build()
        }
    }
}