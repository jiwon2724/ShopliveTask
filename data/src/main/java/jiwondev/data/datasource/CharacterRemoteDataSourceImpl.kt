package jiwondev.data.datasource

import jiwondev.data.api.MarvelCharacterApi
import jiwondev.data.model.CharacterResponse
import javax.inject.Inject

class CharacterRemoteDataSourceImpl @Inject constructor(
    private val characterApi: MarvelCharacterApi
) : CharacterRemoteDataSource {
    override suspend fun getCharacter(
        nameStartsWith: String,
        offset: Int
    ): CharacterResponse {
        return characterApi.getCharacterResponse(
            nameStartsWith = nameStartsWith,
            offset = offset
        )
    }
}