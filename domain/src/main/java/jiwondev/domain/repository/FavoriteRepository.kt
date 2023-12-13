package jiwondev.domain.repository

import jiwondev.domain.model.CharacterInfo

interface FavoriteRepository {
    suspend fun addFavoriteCharacter(characterInfo: CharacterInfo)
    suspend fun deleteFavoriteCharacter(characterInfo: CharacterInfo)
    suspend fun getFavoriteCharacters(): ArrayList<CharacterInfo>
    fun isContains(characterInfo: CharacterInfo): Boolean
}