package jiwndev.feature.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jiwondev.domain.Result
import jiwondev.domain.usecase.CharacterUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val characterUseCase: CharacterUseCase) : ViewModel() {
    suspend fun apiTest() {
        viewModelScope.launch {
            val result = characterUseCase.getCharacter()
            when (result) {
                is Result.Success -> { Log.d("Success : ", "성공") }
                is Result.Error -> { Log.d("Error : ", "${result.exception.message.toString()}") }
            }
        }
    }
}