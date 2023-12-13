package jiwondev.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jiwondev.data.datasource.CharacterLocalDataSource
import jiwondev.data.datasource.CharacterLocalDataSourceImpl
import jiwondev.data.datasource.CharacterRemoteDataSource
import jiwondev.data.datasource.CharacterRemoteDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    fun bindsCharacterRemoteDatasource(characterDataSourceImpl: CharacterRemoteDataSourceImpl): CharacterRemoteDataSource

    @Binds
    fun bindsCharacterLocalDatasource(characterDataSourceImpl: CharacterLocalDataSourceImpl): CharacterLocalDataSource
}