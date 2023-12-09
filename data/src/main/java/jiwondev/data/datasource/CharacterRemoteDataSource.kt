package jiwondev.data.datasource

import jiwondev.data.BuildConfig
import jiwondev.data.model.CharacterResponse

interface CharacterRemoteDataSource {
    suspend fun getCharacter(nameStartsWith: String): CharacterResponse
}