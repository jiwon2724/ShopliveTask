package jiwondev.domain.repository

import jiwondev.domain.model.CharacterInfo

interface FavoriteRepository {
    suspend fun addFavoriteCharacter(characterInfo: CharacterInfo)
    suspend fun deleteFavoriteCharacter(id: Int)
    suspend fun getFavoriteCharacters(): ArrayList<CharacterInfo>
}