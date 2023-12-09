package jiwondev.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jiwondev.data.BuildConfig
import jiwondev.data.api.MarvelCharacterApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val jsonConfig = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(jsonConfig.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder().apply {
            addNetworkInterceptor(loggingInterceptor)
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
        }.build()
    }

    @Singleton
    @Provides
    fun provideCharacterApi(retrofit: Retrofit): MarvelCharacterApi =
        retrofit.create(MarvelCharacterApi::class.java)
}