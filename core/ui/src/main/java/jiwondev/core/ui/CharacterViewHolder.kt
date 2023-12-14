package jiwondev.core.ui

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import jiwondev.core.ui.databinding.ItemCharacterBinding
import jiwondev.domain.model.CharacterInfo

class CharacterViewHolder(
    private val binding: ItemCharacterBinding,
    private val onClick: (CharacterInfo) -> Unit
) : ViewHolder(binding.root) {
    fun bind(characterInfo: CharacterInfo) = with(binding) {
        ivThumbnail.load(characterInfo.thumbnail)
        tvName.text = characterInfo.name
        tvDescription.text = characterInfo.description
        setBackground(characterInfo)
        itemView.setOnClickListener {
            characterInfo.isFavorite = !characterInfo.isFavorite
            setBackground(characterInfo)
            onClick.invoke(characterInfo)
        }
    }
    private fun setBackground(characterInfo: CharacterInfo) {
        if (characterInfo.isFavorite) itemView.setBackgroundColor(Color.LTGRAY) else itemView.setBackgroundColor(Color.WHITE)
    }
}