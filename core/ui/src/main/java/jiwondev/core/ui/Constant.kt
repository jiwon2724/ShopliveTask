package jiwondev.core.ui

import androidx.recyclerview.widget.DiffUtil
import jiwondev.domain.model.CharacterInfo

class Constant {
    companion object {
        internal val CHARACTER_DIFF_CALLBACK = object: DiffUtil.ItemCallback<CharacterInfo>() {
            override fun areItemsTheSame(oldItem: CharacterInfo, newItem: CharacterInfo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CharacterInfo, newItem: CharacterInfo): Boolean {
                return oldItem == newItem
            }
        }
    }
}