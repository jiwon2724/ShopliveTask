package jiwondev.domain.repository

import jiwondev.domain.model.Character

interface CharacterRepository {
    suspend fun getCharacter(nameStartsWith: String): Character
}