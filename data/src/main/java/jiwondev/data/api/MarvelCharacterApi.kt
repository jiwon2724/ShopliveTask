package jiwondev.data.api

import jiwondev.data.BuildConfig
import jiwondev.data.common.Constant
import jiwondev.data.model.CharacterResponse
import retrofit2.http.GET

interface MarvelCharacterApi {
    @GET("characters")
    suspend fun getCharacterResponse(
        ts: String = Constant.timeStamp,
        hash: String = Constant.getHash(),
        apiKey: String = BuildConfig.PUBLIC_KEY,
        nameStartsWith: String,
        limit: Int = 10,
        offset: Int = 10
    ): CharacterResponse
}