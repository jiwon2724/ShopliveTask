package jiwondev.data.mapper

import jiwondev.data.model.CharacterResponse
import jiwondev.domain.model.Character

object CharacterMapper {
    fun mapperToCharacter(characterResponse: CharacterResponse): Character {
        return Character(
            name = characterResponse.data.results.first().name,
            thumbnail = characterResponse.data.results.first().thumbnail.path
        )
    }
}