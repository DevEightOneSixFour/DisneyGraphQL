package com.superspecialapp.disneygraphql.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.superspecialapp.disneygraphql.databinding.ActivityMainBinding
import com.superspecialapp.disneygraphql.presentation.DisneyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}