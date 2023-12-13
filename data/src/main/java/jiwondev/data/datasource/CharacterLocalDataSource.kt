package jiwondev.data.datasource

import jiwondev.domain.model.CharacterInfo

interface CharacterLocalDataSource {
    suspend fun addFavoriteCharacter(characterInfo: CharacterInfo)
    suspend fun deleteFavoriteCharacter(id: Int)
    suspend fun getFavoriteCharacters(): ArrayList<CharacterInfo>
}