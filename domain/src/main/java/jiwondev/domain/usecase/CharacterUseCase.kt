package jiwondev.domain.usecase

import jiwondev.domain.Result
import jiwondev.domain.model.Character
import jiwondev.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterUseCase @Inject constructor(private val repository: CharacterRepository) {
    suspend fun getCharacter(): Result<Character> {
        return repository.getCharacter("man")
    }
}