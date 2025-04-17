package com.example.demonav.ui.main

import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.demonav.R
import com.example.demonav.databinding.MainActBinding
import com.example.demonav.ui.abs.AbsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AbsActivity<MainActBinding>() {
    private val viewModel by viewModels<MainViewModel>()

    override fun initBinding() = MainActBinding.inflate(layoutInflater)

    override fun onViewReady() {

    }
}