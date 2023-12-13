package jiwondev.domain.model

data class Character(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val characterInfo: List<CharacterInfo>
)

data class CharacterInfo(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: String,
    var isFavorite: Boolean = false
)
