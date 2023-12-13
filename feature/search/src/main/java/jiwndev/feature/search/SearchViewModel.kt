package jiwndev.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jiwondev.core.ui.CharacterUiState
import jiwondev.domain.Result
import jiwondev.domain.model.CharacterInfo
import jiwondev.domain.usecase.CharacterUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val characterUseCase: CharacterUseCase) : ViewModel() {
    private var searchJob: Job = Job()

    var pageOffset = 0
        private set

    var resultTotalItem = 0
        private set

    private var searchKeyword: String = ""

    private var _characterState = MutableStateFlow<CharacterUiState>(CharacterUiState.Init)
    val characterState: StateFlow<CharacterUiState>
        get() = _characterState.asStateFlow()


    fun getCharacterData() {
        searchJob.cancel()
        searchJob = viewModelScope.launch {
            _characterState.emit(CharacterUiState.Loading)
            delay(DATA_UPDATE_PERIOD)

            when (val result = characterUseCase.getCharacter(searchKeyword, pageOffset)) {
                is Result.Success -> _characterState.emit(CharacterUiState.LoadSuccess(result.data))
                is Result.Error -> _characterState.emit(CharacterUiState.LoadFail)
            }
        }
    }

    fun loadMore() {
        viewModelScope.launch {
            when (val result = characterUseCase.getCharacter(searchKeyword, pageOffset)) {
                is Result.Success -> _characterState.emit(CharacterUiState.PagingSuccess(result.data))
                is Result.Error -> _characterState.emit(CharacterUiState.LoadFail)
            }
        }
    }

    fun increasePageOffset() {
        pageOffset += PAGE_OFFSET
    }

    fun initPageOffset() {
        pageOffset = 0
    }

    fun setSearchKeyword(search: String) {
        searchKeyword = search
    }

    fun setResultItemCount(count: Int) {
        resultTotalItem = count
    }

    companion object {
        private const val PAGE_OFFSET = 10
        private const val DATA_UPDATE_PERIOD = 300L
    }
}
