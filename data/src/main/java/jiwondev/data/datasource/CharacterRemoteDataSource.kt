package jiwondev.data.datasource

import jiwondev.data.model.CharacterResponse

interface CharacterRemoteDataSource {
    suspend fun getCharacter(nameStartsWith: String, offset: Int): CharacterResponse
}