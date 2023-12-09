package jiwondev.data.api

import jiwondev.data.BuildConfig
import jiwondev.data.model.CharacterResponse
import retrofit2.http.GET

interface MarvelCharacterApi {
    @GET("characters")
    suspend fun getCharacterResponse(
        ts: String = "",
        apiKey: String = BuildConfig.PUBLIC_KEY,
        hash: String = "",
        nameStartsWith: String,
        limit: Int = 10,
        offset: Int = 10
    ): CharacterResponse
}