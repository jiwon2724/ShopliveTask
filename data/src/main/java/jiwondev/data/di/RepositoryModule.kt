package jiwondev.data.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jiwondev.data.repository.CharacterRepositoryImpl
import jiwondev.data.repository.FavoriteRepositoryImpl
import jiwondev.domain.repository.CharacterRepository
import jiwondev.domain.repository.FavoriteRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    abstract fun bindCharacterRepository(characterRepository: CharacterRepositoryImpl): CharacterRepository

    @Binds
    abstract fun bindFavoriteRepository(favoriteRepository: FavoriteRepositoryImpl): FavoriteRepository
}