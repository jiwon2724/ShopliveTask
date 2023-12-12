package jiwndev.feature.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import jiwndev.feature.search.databinding.FragmentSearchBinding
import jiwondev.core.base.BaseFragment
import jiwondev.core.ui.CharacterUiState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    private val searchViewModel: SearchViewModel by viewModels()
    private val characterAdapter: SearchAdapter by lazy { initAdapter() }

    override fun bindingFactory(inflater: LayoutInflater, parent: ViewGroup?): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, parent, false)
    }

    override fun initViews() {
        initRecyclerView()
        startObservingSearch()
        startObservingState()
    }

    private fun initRecyclerView() {
        binding.rvChracter.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = characterAdapter
        }
    }

    private fun startObservingSearch() {
        binding.etSearch.addTextChangedListener { search ->
            if (search.toString().length >= INPUT_SIZE) {
                viewLifecycleOwner.lifecycleScope.launch {
                    searchViewModel.getCharacterData(search.toString().trim())
                }
            }
        }
    }

    private fun startObservingState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                searchViewModel.characterState.collectLatest { state ->
                    when (state) {
                        is CharacterUiState.Init -> Unit

                        is CharacterUiState.LoadSuccess -> {
                            characterAdapter.addCharacterItem(state.data.characterInfo)
                        }

                        is CharacterUiState.LoadFail -> {

                        }

                        is CharacterUiState.Loading -> {

                        }
                    }
                }
            }
        }
    }

    private fun initAdapter(): SearchAdapter {
        return SearchAdapter()
    }

    companion object {
        private const val INPUT_SIZE = 2
    }
}