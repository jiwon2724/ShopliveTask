package jiwondev.data.repository

import jiwondev.data.datasource.CharacterLocalDataSource
import jiwondev.domain.model.CharacterInfo
import jiwondev.domain.repository.FavoriteRepository
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val characterLocalDataSource: CharacterLocalDataSource
) : FavoriteRepository {
    override fun addFavoriteCharacter(characterInfo: CharacterInfo) {
        characterLocalDataSource.addFavoriteCharacter(characterInfo)
    }

    override fun deleteFavoriteCharacter(characterInfo: CharacterInfo) {
        characterLocalDataSource.deleteFavoriteCharacter(characterInfo)
    }

    override suspend fun getFavoriteCharacters(): ArrayList<CharacterInfo> {
        return characterLocalDataSource.getFavoriteCharacters()
    }

    override fun isContains(characterInfo: CharacterInfo): Boolean {
        return characterLocalDataSource.isContains(characterInfo)
    }
}