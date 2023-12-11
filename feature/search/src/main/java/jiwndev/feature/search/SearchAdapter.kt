package jiwndev.feature.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import jiwondev.core.ui.CharacterViewHolder
import jiwondev.core.ui.Constant
import jiwondev.core.ui.databinding.ItemCharacterBinding
import jiwondev.domain.model.CharacterInfo

class SearchAdapter(
    private val characters: List<CharacterInfo>,
    private val onClick: (Int) -> Unit
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

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characters[position])
    }
}