package jiwondev.core.ui

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import jiwondev.core.ui.databinding.ItemCharacterBinding
import jiwondev.domain.model.CharacterInfo

class CharacterViewHolder(
    private val binding: ItemCharacterBinding,
    private val onClick: (CharacterInfo, Int, View) -> Unit
) : ViewHolder(binding.root) {
    fun bind(characterInfo: CharacterInfo) {
        binding.apply {
            ivThumbnail.load(characterInfo.thumbnail)
            tvName.text = characterInfo.name
            tvDescription.text = characterInfo.description
            if (characterInfo.isFavorite) itemView.setBackgroundColor(Color.LTGRAY) else itemView.setBackgroundColor(Color.WHITE)
            itemView.setOnClickListener {
                characterInfo.isFavorite = !characterInfo.isFavorite
                if (characterInfo.isFavorite) itemView.setBackgroundColor(Color.LTGRAY) else itemView.setBackgroundColor(Color.WHITE)
                onClick.invoke(characterInfo, adapterPosition, itemView)
            }
        }
    }
}