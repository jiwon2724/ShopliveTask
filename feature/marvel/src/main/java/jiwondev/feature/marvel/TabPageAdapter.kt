package jiwondev.feature.marvel

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import jiwndev.feature.search.SearchFragment
import jiwondev.feature.favorite.FavoriteFragment

class TabPageAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = FragmentType.values().count()

    override fun createFragment(position: Int): Fragment {
        return when(FragmentType.values()[position]) {
            FragmentType.FAVORITE -> FavoriteFragment()
            FragmentType.SEARCH -> SearchFragment()
        }
    }
}