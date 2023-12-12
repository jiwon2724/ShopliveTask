package jiwondev.data.datasource

import android.util.Log
import jiwondev.data.api.MarvelCharacterApi
import jiwondev.data.model.CharacterResponse
import retrofit2.Response
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