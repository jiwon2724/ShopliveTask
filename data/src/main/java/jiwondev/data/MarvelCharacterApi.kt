package jiwondev.data

import androidx.resourceinspection.annotation.Attribute.IntMap
import jiwondev.data.response.CharacterResponse
import retrofit2.http.GET

interface MarvelCharacterApi {
    @GET("characters")
    suspend fun getCharacterResponse(
        ts: String,
        apiKey: String = BuildConfig.PUBLIC_KEY,
        hash: String,
        nameStartsWith: String,
        limit: Int,
        offset: Int
    ): CharacterResponse
}