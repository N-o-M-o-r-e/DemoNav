package com.example.demonav.ui.main.home.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.example.demonav.databinding.DashboardFrBinding
import com.example.demonav.ui.abs.AbsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : AbsFragment<DashboardFrBinding>() {

    private val viewModel by viewModels<DashboardViewModel>()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = DashboardFrBinding.inflate(
        layoutInflater,
        container,
        false
    )

    override fun onViewReady() {
        val textView: TextView = binding.textDashboard
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
    }
}