package jiwondev.data.api

import jiwondev.data.BuildConfig
import jiwondev.data.common.Constant
import jiwondev.data.model.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelCharacterApi {
    @GET("characters")
    suspend fun getCharacterResponse(
        @Query("ts") ts: String = Constant.timeStamp,
        @Query("hash") hash: String = Constant.getHash(),
        @Query("apiKey") apiKey: String = BuildConfig.PUBLIC_KEY,
        @Query("nameStartsWith") nameStartsWith: String,
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int = 10
    ): CharacterResponse
}