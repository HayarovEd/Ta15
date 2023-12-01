package zppdenga.creditplus.domain.di

import zppdenga.creditplus.data.remote.ApiTa
import zppdenga.creditplus.domain.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)

object ApiModule {
    @Provides
    @Singleton
    fun provideApi(): ApiTa {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiTa::class.java)
    }
}