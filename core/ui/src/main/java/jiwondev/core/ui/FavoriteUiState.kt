package jiwondev.core.ui

import jiwondev.domain.model.CharacterInfo

sealed class FavoriteUiState {
    object Init : FavoriteUiState()
    data class Delete(val data: CharacterInfo) : FavoriteUiState()
    data class Add(val data: CharacterInfo) : FavoriteUiState()
    data class Favorite(val data: ArrayList<CharacterInfo>) : FavoriteUiState()
}
