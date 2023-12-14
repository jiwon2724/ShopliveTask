package jiwondev.core.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jiwondev.domain.model.CharacterInfo
import jiwondev.domain.usecase.FavoriteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val favoriteUseCase: FavoriteUseCase) : ViewModel() {
    private var _favoriteState = MutableStateFlow<FavoriteUiState>(FavoriteUiState.Init)
    val favoriteState: StateFlow<FavoriteUiState>
        get() = _favoriteState.asStateFlow()

    fun getFavoriteCharacter() {
        viewModelScope.launch {
            _favoriteState.emit(FavoriteUiState.Favorite(favoriteUseCase.getFavoriteCharacters()))
        }
    }

    fun addCharacter(characterInfo: CharacterInfo) {
        viewModelScope.launch {
            val favorites = favoriteUseCase.getFavoriteCharacters()
            if (favorites.size >= FAVORITE_MAX_SIZE) {
                favoriteUseCase.deleteFavoriteCharacter(favorites.removeFirst())
            }
            favoriteUseCase.addFavoriteCharacter(characterInfo)
            _favoriteState.emit(FavoriteUiState.Add(characterInfo))
        }
    }

    fun deleteCharacter(characterInfo: CharacterInfo) {
        viewModelScope.launch {
            favoriteUseCase.deleteFavoriteCharacter(characterInfo)
            _favoriteState.emit(FavoriteUiState.Delete(characterInfo))
        }
    }

    fun isCharacterContains(characterInfo: CharacterInfo): Boolean {
        return favoriteUseCase.isCharacterContains(characterInfo)
    }

    companion object {
        private const val FAVORITE_MAX_SIZE = 5
    }
}