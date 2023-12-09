package jiwondev.feature.marvel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import jiwondev.feature.marvel.databinding.ActivityMarvelBinding

@AndroidEntryPoint
class MarvelActivity : AppCompatActivity() {
    private val binding: ActivityMarvelBinding by lazy { ActivityMarvelBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()
    }

    private fun initView() = with(binding) {
        vpTabs.adapter = TabPageAdapter(this@MarvelActivity)
        TabLayoutMediator(layoutTab, vpTabs) { tab, position ->
            when (FragmentType.values()[position]) {
                FragmentType.SEARCH -> tab.text = "SEARCH"
                FragmentType.FAVORITE -> tab.text = "FAVORITE"
            }
        }.attach()
    }
}