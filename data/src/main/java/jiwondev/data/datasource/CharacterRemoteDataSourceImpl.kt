package jiwondev.data.datasource

import jiwondev.data.api.MarvelCharacterApi
import jiwondev.data.model.CharacterResponse
import javax.inject.Inject

class CharacterRemoteDataSourceImpl @Inject constructor(
    private val characterApi: MarvelCharacterApi
) : CharacterRemoteDataSource {
    override suspend fun getCharacter(
        ts: String,
        apiKey: String,
        hash: String,
        nameStartsWith: String,
        limit: Int,
        offset: Int
    ): CharacterResponse = characterApi.getCharacterResponse(nameStartsWith = nameStartsWith)
}