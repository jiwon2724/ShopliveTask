package jiwndev.feature.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import jiwndev.feature.search.databinding.FragmentSearchBinding
import jiwondev.core.base.BaseFragment

class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    override fun bindingFactory(inflater: LayoutInflater, parent: ViewGroup?): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, parent, false)
    }

    override fun initViews() { }
}