package com.example.demonav.ui.main.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.demonav.databinding.DetailFrBinding
import com.example.demonav.ui.abs.AbsFragment
import com.example.demonav.ui.ktx.logMessage
import com.example.demonav.ui.navigate.Action
import com.example.demonav.ui.navigate.MainNavigateAction
import com.example.demonav.ui.navigate.result.UpdateResult
import com.example.demonav.ui.navigate.result.removeFragmentResult
import com.example.demonav.ui.navigate.result.setFragmentResultListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : AbsFragment<DetailFrBinding>() {

    private val viewModel by viewModels<DetailViewModel>()

    @Inject
    lateinit var mainNavigateAction: MainNavigateAction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        removeFragmentResult(UpdateResult.KEY)
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = DetailFrBinding.inflate(
        layoutInflater,
        container,
        false
    )

    override fun onViewReady() {
        initHandleState()
        initHandleEvents()
        initActions()
    }

    private fun initActions() {
        binding.btnBack.setOnClickListener {
            mainNavigateAction.popBackStack()
        }
        binding.btnEdit.setOnClickListener {
            mainNavigateAction.navigate(
                Action.DetailToUpdate(
                    args = viewModel.stateValue.toUpdateArgss()
                )
            )
        }
    }

    private fun initHandleState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.stateFlow
                .map { it.isLoading }
                .distinctUntilChanged()
                .collectLatest {
                    binding.loading.isVisible = it
                }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.stateFlow
                .mapNotNull { it.userInfo }
                .distinctUntilChanged()
                .collectLatest {
                    binding.nameTv.text = it.name
                    binding.emailTv.text = it.email
                }
        }
    }

    private fun initHandleEvents() {
        setFragmentResultListener<UpdateResult>(UpdateResult.KEY) { result ->
            logMessage("setFragmentResultListener ${result}")
            result.let(DetailAction::UpdateUserInfo)
                .let(viewModel::dispatch)
        }
    }
}