package com.example.demonav.ui.abs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class AbsActivity<B : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: B

    protected abstract fun initBinding(): B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = initBinding()
        setContentView(binding.root)
        onViewReady()
    }

    protected abstract fun onViewReady()
}