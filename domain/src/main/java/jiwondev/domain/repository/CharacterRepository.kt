package jiwondev.domain.repository

import jiwondev.domain.Result
import jiwondev.domain.model.Character

interface CharacterRepository {
    suspend fun getCharacter(
        nameStartsWith: String,
        offset: Int
    ): Result<Character>
}