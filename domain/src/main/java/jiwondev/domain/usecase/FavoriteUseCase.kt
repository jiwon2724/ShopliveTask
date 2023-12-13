package jiwondev.domain.usecase

import jiwondev.domain.model.CharacterInfo
import jiwondev.domain.repository.FavoriteRepository
import javax.inject.Inject

class FavoriteUseCase @Inject constructor(private val favoriteRepository: FavoriteRepository) {
    suspend fun addFavoriteCharacter(characterInfo: CharacterInfo) {
        favoriteRepository.addFavoriteCharacter(characterInfo)
    }

    suspend fun deleteFavoriteCharacter(id: Int) {
        favoriteRepository.deleteFavoriteCharacter(id)
    }

    suspend fun getFavoriteCharacters(): ArrayList<CharacterInfo> {
        return favoriteRepository.getFavoriteCharacters()
    }
}