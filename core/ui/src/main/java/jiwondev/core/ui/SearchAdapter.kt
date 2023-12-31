package jiwondev.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import jiwondev.core.ui.databinding.ItemCharacterBinding
import jiwondev.domain.model.CharacterInfo

class SearchAdapter(
    private val onClick: (CharacterInfo) -> Unit
) : ListAdapter<CharacterInfo, CharacterViewHolder>(Constant.CHARACTER_DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            binding = ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClick = onClick
        )
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
    }

    fun addCharacterItem(newItem: List<CharacterInfo>) {
        val currentList = currentList.toMutableList()
        currentList.addAll(newItem)
        currentList.distinct()
        submitList(currentList)
    }

    fun addFavoriteCharacterItem(newItem: CharacterInfo) {
        val currentList = currentList.toMutableList()
        if (currentList.size >= FAVORITE_MAX_SIZE) currentList.removeFirst()
        currentList.add(newItem)
        submitList(currentList)
    }

    fun removeCharacterItem(removeItem: CharacterInfo) {
        val currentList = currentList.toMutableList()
        currentList.remove(removeItem)
        submitList(currentList)
    }

    companion object {
        private const val FAVORITE_MAX_SIZE = 5
    }
}