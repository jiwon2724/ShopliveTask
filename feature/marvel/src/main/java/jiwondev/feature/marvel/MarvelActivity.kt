package jiwondev.feature.marvel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jiwondev.feature.marvel.databinding.ActivityMarvelBinding

class MarvelActivity : AppCompatActivity() {
    private val binding: ActivityMarvelBinding by lazy { ActivityMarvelBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}