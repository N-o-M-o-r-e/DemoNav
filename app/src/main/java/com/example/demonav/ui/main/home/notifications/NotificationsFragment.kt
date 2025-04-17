package com.example.demonav.ui.main.home.notifications

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.example.demonav.databinding.NotificationsFrBinding
import com.example.demonav.ui.abs.AbsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationsFragment : AbsFragment<NotificationsFrBinding>() {

    private val viewModel by viewModels<NotificationsViewModel>()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = NotificationsFrBinding.inflate(
        layoutInflater,
        container,
        false
    )

    override fun onViewReady() {
        val textView: TextView = binding.textNotifications
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
    }
}