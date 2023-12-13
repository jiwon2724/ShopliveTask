package jiwondev.core.ui

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import jiwondev.core.ui.databinding.ItemCharacterBinding
import jiwondev.domain.model.CharacterInfo

class CharacterViewHolder(
    private val binding: ItemCharacterBinding,
    private val onClick: (CharacterInfo) -> Unit
) : ViewHolder(binding.root) {
    fun bind(characterInfo: CharacterInfo) {
        binding.apply {
            ivThumbnail.load(characterInfo.thumbnail)
            tvName.text = characterInfo.name
            tvDescription.text = characterInfo.description
            itemView.setOnClickListener { onClick.invoke(characterInfo) }
        }
    }
}