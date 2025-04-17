package com.example.demonav.ui.main.home.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.example.demonav.databinding.HomeFrBinding
import com.example.demonav.ui.abs.AbsFragment
import com.example.demonav.ui.navigate.Action
import com.example.demonav.ui.navigate.MainNavigateAction
import com.example.demonav.ui.navigate.args.DetailArgs
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject

@AndroidEntryPoint
class HomeFragment : AbsFragment<HomeFrBinding>() {

    private val viewModel by viewModels<HomeViewModel>()

    @Inject
    lateinit var mainNavigateAction: MainNavigateAction

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = HomeFrBinding.inflate(
        layoutInflater,
        container,
        false
    )

    override fun onViewReady() {
        val textView: TextView = binding.textHome
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        initActions()
    }

    private fun initActions() {
        binding.btnDetail.setOnClickListener {
            mainNavigateAction.navigate(
                Action.HomeToDetail(
                    args = DetailArgs(
                        userId = "Hello Detail Fragment",
                        userName = "Nguyen Van A"
                    )
                )
            )
        }
        binding.btnSetting.setOnClickListener {
            mainNavigateAction.navigate(Action.HomeToSetting)
        }
    }
}