package jiwondev.core.ui

import jiwondev.domain.model.Character

sealed class CharacterUiState {
    object Init : CharacterUiState()
    data class LoadSuccess(val data: Character) : CharacterUiState()
    object LoadFail : CharacterUiState()
    object Loading: CharacterUiState()
}
