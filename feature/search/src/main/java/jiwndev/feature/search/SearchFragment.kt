package jiwndev.feature.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import jiwndev.feature.search.databinding.FragmentSearchBinding
import jiwondev.core.base.BaseFragment
import jiwondev.core.ui.CharacterUiState
import jiwondev.core.ui.SearchAdapter
import jiwondev.core.ui.SharedViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    private val searchViewModel: SearchViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val characterAdapter: SearchAdapter by lazy {
        SearchAdapter { characterInfo, position, view ->
            if (sharedViewModel.isContains(characterInfo)) {
                sharedViewModel.deleteCharacter(characterInfo)
            } else {
                sharedViewModel.addCharacter(characterInfo)
            }
        }
    }

    override fun bindingFactory(inflater: LayoutInflater, parent: ViewGroup?): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, parent, false)
    }

    override fun initViews() {
        initRecyclerView()
        startObservingSearch()
        startObservingState()
        startPagination()
    }

    private fun initRecyclerView() {
        binding.rvChracter.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = characterAdapter
            setHasFixedSize(true)
        }
    }

    private fun startObservingSearch() {
        binding.etSearch.addTextChangedListener { search ->
            if (search.toString().length >= INPUT_SIZE) {
                searchViewModel.apply {
                    setSearchKeyword(search.toString().trim())
                    searchViewModel.getCharacterData()
                }
            }
        }
    }

    private fun startObservingState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    searchViewModel.characterState.collectLatest { state ->
                        handleCharacterUiState(state)
                    }
                }

//                launch {
//                    sharedViewModel.favoriteState.collectLatest { state ->
//                        when (state) {
//                            is FavoriteUiState.Init -> Unit
//                            is FavoriteUiState.Favorite -> {
//                                characterAdapter.apply {
//                                    addCharacterItem(state.data)
//                                }
//                            }
//                            is FavoriteUiState.Delete -> {
//                                characterAdapter.removeCharacterItem(state.data)
//                            }
//                            is FavoriteUiState.Add -> {
//                                characterAdapter.addFavoriteCharacterItem(state.data)
//                            }
//                        }
//                    }
//                }
            }
        }
    }

    private fun handleCharacterUiState(state: CharacterUiState) {
        when (state) {
            is CharacterUiState.Init -> Unit
            is CharacterUiState.Loading -> binding.progress.visibility = View.VISIBLE
            is CharacterUiState.LoadFail -> handleLoadFail()
            is CharacterUiState.LoadSuccess -> handleLoadSuccess(state)
            is CharacterUiState.PagingSuccess -> handlePagingSuccess(state)
        }
    }

    private fun handleLoadFail() {
        binding.progress.visibility = View.GONE
        Toast.makeText(requireContext(), "데이터 불러오기에 실패했습니다.", Toast.LENGTH_SHORT).show()
    }

    private fun handleLoadSuccess(state: CharacterUiState.LoadSuccess) {
        binding.progress.visibility = View.GONE
        searchViewModel.apply {
            initPageOffset()
            setResultItemCount(state.data.count)
        }
        characterAdapter.apply {
            submitList(null)
            addCharacterItem(state.data.characterInfo)
        }
    }

    private fun handlePagingSuccess(state: CharacterUiState.PagingSuccess) {
        binding.progress.visibility = View.GONE
        characterAdapter.addCharacterItem(state.data.characterInfo)
    }

    private fun startPagination() = with(binding) {
        rvChracter.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (rvChracter.canScrollVertically(SCROLL_END)) {
                    if (searchViewModel.resultTotalItem >= searchViewModel.pageOffset) {
                        searchViewModel.apply {
                            increasePageOffset()
                            loadMore()
                        }
                    }
                }
            }
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) { }
        })
    }

    companion object {
        private const val INPUT_SIZE = 2
        private const val SCROLL_END = 1
    }
}