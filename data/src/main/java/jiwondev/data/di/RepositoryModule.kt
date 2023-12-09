package jiwondev.data.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jiwondev.data.repository.CharacterRepositoryImpl
import jiwondev.domain.repository.CharacterRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    abstract fun bindCharacterRepository(characterRepository: CharacterRepositoryImpl): CharacterRepository
}