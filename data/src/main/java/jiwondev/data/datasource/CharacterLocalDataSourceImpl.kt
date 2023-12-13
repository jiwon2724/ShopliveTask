package jiwondev.data.datasource

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import jiwondev.data.common.Constant.CHARACTER
import jiwondev.domain.model.CharacterInfo
import javax.inject.Inject

class CharacterLocalDataSourceImpl @Inject constructor(
    private val preference: SharedPreferences
) : CharacterLocalDataSource {
    override fun addFavoriteCharacter(characterInfo: CharacterInfo) {
        preference.edit().apply {
            val addResult = getFavoriteCharacters().apply { add(characterInfo) }
            putString(CHARACTER, Gson().toJson(addResult))
            apply()
        }
    }

    override fun deleteFavoriteCharacter(characterInfo: CharacterInfo) {
        preference.edit().apply {
            val deleteResult = getFavoriteCharacters()
            deleteResult.removeIf { it.id == characterInfo.id }
            putString(CHARACTER, Gson().toJson(deleteResult))
            apply()
        }
    }

    override fun getFavoriteCharacters(): ArrayList<CharacterInfo> {
        val characters = preference.getString(CHARACTER, null)
        val type = object : TypeToken<ArrayList<CharacterInfo>>() {}.type
        return Gson().fromJson(characters, type) ?: arrayListOf()
    }

    override fun isContains(characterInfo: CharacterInfo): Boolean =
        getFavoriteCharacters().contains(characterInfo.copy(isFavorite = true))
}