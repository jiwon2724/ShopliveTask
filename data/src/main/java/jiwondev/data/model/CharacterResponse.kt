package jiwondev.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val data: Data,
    val etag: String,
    val status: String
)

@Serializable
data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)

@Serializable
data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<Result>,
    val total: Int
)

@Serializable
data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)

@Serializable
data class Item(
    val name: String,
    val resourceURI: String
)

@Serializable
data class ItemXXX(
    val name: String,
    val resourceURI: String,
    val type: String
)

@Serializable
data class Result(
    val comics: Comics,
    val description: String,
    val events: Events,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val series: Series,
    val stories: Stories,
    val thumbnail: Thumbnail,
    val urls: List<Url>
)

@Serializable
data class Series(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)

@Serializable
data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXXX>,
    val returned: Int
)

@Serializable
data class Thumbnail(
    val extension: String,
    val path: String
)

@Serializable
data class Url(
    val type: String,
    val url: String
)

