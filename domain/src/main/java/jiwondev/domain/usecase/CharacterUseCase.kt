package jiwondev.domain.usecase

import jiwondev.domain.model.Character
import jiwondev.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterUseCase @Inject constructor(private val repository: CharacterRepository) {
    suspend fun getCharacter(): Character {
        return repository.getCharacter()
    }
}