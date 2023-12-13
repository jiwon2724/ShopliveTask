package jiwondev.domain.repository

import jiwondev.domain.model.CharacterInfo

interface FavoriteRepository {
    fun addFavoriteCharacter(characterInfo: CharacterInfo)
    fun deleteFavoriteCharacter(characterInfo: CharacterInfo)
    suspend fun getFavoriteCharacters(): ArrayList<CharacterInfo>
    fun isContains(characterInfo: CharacterInfo): Boolean
}