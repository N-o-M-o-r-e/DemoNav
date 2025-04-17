package com.example.demonav.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.demonav.R
import com.example.demonav.databinding.HomeContainerFrBinding
import com.example.demonav.ui.abs.AbsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeContainerFragment : AbsFragment<HomeContainerFrBinding>() {

    private val viewModel by viewModels<HomeContainerViewModel>()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = HomeContainerFrBinding.inflate(
        layoutInflater,
        container,
        false
    )

    override fun onViewReady() {
        initHomeNav()
    }

    private fun initHomeNav() {
        val navView: BottomNavigationView = binding.homeNavView
        val navController = NavHostFragment.findNavController(
            childFragmentManager.findFragmentById(
                R.id.nav_host_home_container_fragment
            )!!
        )
        navView.setupWithNavController(navController)
    }
}