package com.example.demonav.ui.main.setting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.demonav.R
import com.example.demonav.databinding.HomeContainerFrBinding
import com.example.demonav.databinding.SettingFrBinding
import com.example.demonav.ui.abs.AbsFragment
import com.example.demonav.ui.navigate.MainNavigateAction
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject

@AndroidEntryPoint
class SettingFragment : AbsFragment<SettingFrBinding>() {

    private val viewModel by viewModels<SettingViewModel>()

    @Inject
    lateinit var mainNavigateAction: MainNavigateAction

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = SettingFrBinding.inflate(
        layoutInflater,
        container,
        false
    )

    override fun onViewReady() {
        binding.btnBack.setOnClickListener {
            mainNavigateAction.popBackStack()
        }
    }
}