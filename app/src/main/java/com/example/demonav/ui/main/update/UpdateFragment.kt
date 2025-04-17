package com.example.demonav.ui.main.update

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.demonav.databinding.UpdateFrBinding
import com.example.demonav.ui.abs.AbsFragment
import com.example.demonav.ui.ktx.logMessage
import com.example.demonav.ui.navigate.Action
import com.example.demonav.ui.navigate.MainNavigateAction
import com.example.demonav.ui.navigate.result.UpdateResult
import com.example.demonav.ui.navigate.result.setFragmentResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class UpdateFragment : AbsFragment<UpdateFrBinding>() {

    private val viewModel by viewModels<UpdateViewModel>()

    @Inject
    lateinit var mainNavigateAction: MainNavigateAction

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = UpdateFrBinding.inflate(
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
        binding.btnSubmit.setOnClickListener {
            viewModel.dispatch(UpdateAction.UpdateUserInfo)
        }

        binding.btnDelete.setOnClickListener {
            viewModel.stateValue.setFragmentResult().let {
                logMessage("setFragmentResult ${it}")
                setFragmentResult(UpdateResult.KEY, it)
            }
            mainNavigateAction.navigate(
                Action.UpdatePopToHome
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
                    binding.nameTv.setText(it.name)
                    binding.emailTv.setText(it.email)
                }
        }
    }

    private fun initHandleEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.eventFlow.collectLatest { event ->
                when (event) {
                    UpdateEvent.UpdateSuccess -> {
                        viewModel.stateValue.setFragmentResult().let {
                            logMessage("setFragmentResult ${it}")
                            setFragmentResult(UpdateResult.KEY, it)
                        }
                        mainNavigateAction.popBackStack()
                    }
                }
            }
        }
    }

}