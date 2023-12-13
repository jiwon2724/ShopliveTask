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
    var itemPosition: Int = 0
        private set

    var itemSize: Int = 0
        private set

    lateinit var currentCharacterInfo: CharacterInfo
        private set

    private var _favoriteState = MutableStateFlow<FavoriteUiState>(FavoriteUiState.Init)
    val favoriteState: StateFlow<FavoriteUiState>
        get() = _favoriteState.asStateFlow()

    fun getFavoriteCharacter() {
        viewModelScope.launch {
            _favoriteState.emit(FavoriteUiState.Favorite(favoriteUseCase.getFavoriteCharacters()))
        }
    }

    fun addCharacter(characterInfo: CharacterInfo) {
        favoriteUseCase.addFavoriteCharacter(characterInfo)
        viewModelScope.launch {
            _favoriteState.emit(FavoriteUiState.Add(characterInfo))
        }
    }

    fun deleteCharacter(characterInfo: CharacterInfo) {
        favoriteUseCase.deleteFavoriteCharacter(characterInfo)
        viewModelScope.launch {
            _favoriteState.emit(FavoriteUiState.Delete(characterInfo))
        }
    }

    fun isContains(characterInfo: CharacterInfo): Boolean {
        return favoriteUseCase.isCharacterContains(characterInfo)
    }

    fun setItemPosition(position: Int) {
        itemPosition = position
    }

    fun setCurrentCharacterInfo(characterInfo: CharacterInfo) {
        currentCharacterInfo = characterInfo
    }

    fun setItemSize(size: Int) {
        itemSize = size
    }
}