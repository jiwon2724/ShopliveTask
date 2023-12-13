package jiwondev.data.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

private const val MARVEL_SHARED_PREFERENCE = "marvel_shared_preference"

@InstallIn(SingletonComponent::class)
@Module
object StorageModule {
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(MARVEL_SHARED_PREFERENCE, Context.MODE_PRIVATE)
    }
}