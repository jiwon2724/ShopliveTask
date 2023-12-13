package jiwondev.feature.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import jiwondev.core.base.BaseFragment
import jiwondev.core.ui.FavoriteUiState
import jiwondev.core.ui.SearchAdapter
import jiwondev.core.ui.SharedViewModel
import jiwondev.feature.favorite.databinding.FragmentFavoriteBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val characterAdapter: SearchAdapter by lazy {
        SearchAdapter { characterInfo, position, view ->
            sharedViewModel.deleteCharacter(characterInfo)
        }
    }

    override fun bindingFactory(inflater: LayoutInflater, parent: ViewGroup?): FragmentFavoriteBinding {
        return FragmentFavoriteBinding.inflate(inflater, parent, false)
    }

    override fun initViews() {
        initRecyclerView()
        sharedViewModel.getFavoriteCharacter()
        startObservingFavorite()
    }

    private fun initRecyclerView() {
        binding.rvFavorite.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = characterAdapter
        }
    }

    private fun startObservingFavorite() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                sharedViewModel.favoriteState.collectLatest { state ->
                    when (state) {
                        is FavoriteUiState.Init -> Unit
                        is FavoriteUiState.Favorite ->  {
                            characterAdapter.addCharacterItem(state.data)
                            toggleEmptyMessageVisibility()
                        }
                        is FavoriteUiState.Delete -> {
                            characterAdapter.removeCharacterItem(state.data)
                            toggleEmptyMessageVisibility()
                        }
                        is FavoriteUiState.Add -> {
                            characterAdapter.addFavoriteCharacterItem(state.data)
                            binding.tvEmptyCharacter.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    private fun toggleEmptyMessageVisibility() {
        if (characterAdapter.itemCount-1 == 0 || characterAdapter.itemCount-1 == -1) {
            binding.tvEmptyCharacter.visibility = View.VISIBLE
        } else {
            binding.tvEmptyCharacter.visibility = View.GONE
        }
    }
}