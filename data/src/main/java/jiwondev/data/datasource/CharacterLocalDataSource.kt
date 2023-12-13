package jiwondev.data.datasource

import jiwondev.domain.model.CharacterInfo

interface CharacterLocalDataSource {
    fun addFavoriteCharacter(characterInfo: CharacterInfo)
    fun deleteFavoriteCharacter(characterInfo: CharacterInfo)
    fun getFavoriteCharacters(): ArrayList<CharacterInfo>
    fun isContains(characterInfo: CharacterInfo): Boolean
}