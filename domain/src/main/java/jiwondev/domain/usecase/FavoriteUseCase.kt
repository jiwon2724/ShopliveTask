package jiwondev.domain.usecase

import jiwondev.domain.model.CharacterInfo
import jiwondev.domain.repository.FavoriteRepository
import javax.inject.Inject

class FavoriteUseCase @Inject constructor(private val favoriteRepository: FavoriteRepository) {
    fun addFavoriteCharacter(characterInfo: CharacterInfo) {
        favoriteRepository.addFavoriteCharacter(characterInfo)
    }

    fun deleteFavoriteCharacter(characterInfo: CharacterInfo) {
        favoriteRepository.deleteFavoriteCharacter(characterInfo)
    }

    suspend fun getFavoriteCharacters(): ArrayList<CharacterInfo> {
        return favoriteRepository.getFavoriteCharacters()
    }

    fun isCharacterContains(characterInfo: CharacterInfo): Boolean {
        return favoriteRepository.isContains(characterInfo)
    }
}