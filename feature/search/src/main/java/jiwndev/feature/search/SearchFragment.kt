package jiwndev.feature.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jiwndev.feature.search.databinding.FragmentSearchBinding
import jiwondev.core.base.BaseFragment

class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    override fun bindingFactory(inflater: LayoutInflater, parent: ViewGroup?): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, parent, false)
    }

    override fun initViews() { }
}