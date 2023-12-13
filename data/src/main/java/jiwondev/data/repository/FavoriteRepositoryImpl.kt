package jiwondev.data.repository

import jiwondev.data.datasource.CharacterLocalDataSource
import jiwondev.domain.model.CharacterInfo
import jiwondev.domain.repository.FavoriteRepository
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val characterLocalDataSource: CharacterLocalDataSource
) : FavoriteRepository {
    override suspend fun addFavoriteCharacter(characterInfo: CharacterInfo) {
        characterLocalDataSource.addFavoriteCharacter(characterInfo)
    }

    override suspend fun deleteFavoriteCharacter(id: Int) {
        characterLocalDataSource.deleteFavoriteCharacter(id)
    }

    override suspend fun getFavoriteCharacters(): ArrayList<CharacterInfo> {
        return characterLocalDataSource.getFavoriteCharacters()
    }
}