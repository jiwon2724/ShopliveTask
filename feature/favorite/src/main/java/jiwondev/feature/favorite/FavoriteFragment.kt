package jiwondev.feature.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import jiwondev.core.base.BaseFragment
import jiwondev.feature.favorite.databinding.FragmentFavoriteBinding

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {
    override fun bindingFactory(inflater: LayoutInflater, parent: ViewGroup?): FragmentFavoriteBinding {
        return FragmentFavoriteBinding.inflate(inflater, parent, false)
    }

    override fun initViews() { }

    companion object {
        fun getInstance() = FavoriteFragment()
    }
}