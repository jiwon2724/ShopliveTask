package jiwondev.data.mapper

import jiwondev.data.model.CharacterResponse
import jiwondev.data.model.Result
import jiwondev.domain.model.Character
import jiwondev.domain.model.CharacterInfo

object CharacterMapper {
    fun mapperToCharacter(characterResponse: CharacterResponse): Character {
        return Character(
            offset = characterResponse.data.offset,
            limit = characterResponse.data.limit,
            total = characterResponse.data.total,
            count = characterResponse.data.count,
            characterInfo = characterResponse.data.results.toCharacterInfo()
        )
    }

    private fun List<Result>.toCharacterInfo(): List<CharacterInfo> {
        return map { result ->
            CharacterInfo(
                id = result.id,
                name = result.name,
                description = result.description,
                thumbnail = "${result.thumbnail.path}.${result.thumbnail.extension}".replace("http", "https")
            )
        }
    }
}
