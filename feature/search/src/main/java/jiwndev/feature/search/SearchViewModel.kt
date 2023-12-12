package jiwndev.feature.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jiwondev.core.ui.CharacterUiState
import jiwondev.domain.Result
import jiwondev.domain.model.Character
import jiwondev.domain.model.CharacterInfo
import jiwondev.domain.usecase.CharacterUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val characterUseCase: CharacterUseCase) : ViewModel() {
    var pageOffset = 0
        private set

    val characters: ArrayList<CharacterInfo> = arrayListOf()

    private var _characterState = MutableStateFlow<CharacterUiState>(CharacterUiState.Init)
    val characterState: StateFlow<CharacterUiState>
        get() = _characterState.asStateFlow()


    suspend fun getCharacterData(search: String) {
        viewModelScope.launch {
            _characterState.emit(CharacterUiState.Loading)
            val characterResponse = async {
                delay(300)
                characterUseCase.getCharacter(search, pageOffset)
            }
            println("호출! $search")
            when (val result = characterResponse.await()) {
                is Result.Success -> {
                    characters.addAll(result.data.characterInfo)
                    _characterState.emit(CharacterUiState.LoadSuccess(result.data))
                }
                is Result.Error -> _characterState.emit(CharacterUiState.LoadFail)
            }
        }
    }

    fun increasePageOffset() {
        pageOffset += 10
    }
}
