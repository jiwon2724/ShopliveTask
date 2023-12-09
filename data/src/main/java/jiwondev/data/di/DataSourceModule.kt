package jiwondev.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jiwondev.data.datasource.CharacterRemoteDataSource
import jiwondev.data.datasource.CharacterRemoteDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    fun bindsCharacterDatasource(characterDataSourceImpl: CharacterRemoteDataSourceImpl): CharacterRemoteDataSource
}