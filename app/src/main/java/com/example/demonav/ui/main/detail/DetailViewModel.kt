package com.example.demonav.ui.main.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demonav.data.model.UserInfo
import com.example.demonav.ui.navigate.args.DetailArgs
import com.example.demonav.ui.navigate.args.getArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    // lay du lieu tu SavedStateHandle
    private val args: DetailArgs? by lazy { savedStateHandle.getArgs() }

    private val state by lazy { MutableStateFlow(args.toState()) }

    val stateFlow by lazy { state.asStateFlow() }

    val stateValue by lazy { state.value }

    private val _action by lazy { MutableSharedFlow<DetailAction>() }

    private inline fun <reified A : DetailAction> action() = _action.filterIsInstance<A>()

    init {
        // init UiState with args

        // init handle functions
        initHandleLoadUserInfo()

        // init dispatch action
        dispatch(DetailAction.LoadUserInfo)
    }

    fun dispatch(action: DetailAction) {
        viewModelScope.launch { _action.emit(action) }
    }

    private fun initHandleLoadUserInfo() {
        action<DetailAction.LoadUserInfo>()
            .onEach {
                state.update { it.copy(isLoading = true) }
                delay(1000)
                state.update {
                    it.copy(
                        userInfo = UserInfo(
                            name = "Nguyen Van A",
                            email = "a@gmail.com"
                        ),
                        isLoading = false
                    )
                }
            }
            .launchIn(viewModelScope)

        action<DetailAction.UpdateUserInfo>()
            .onEach { action ->
                state.update {
                    it.copy(
                        userInfo = it.userInfo?.copy(
                            name = action.result.name,
                            email = action.result.email
                        )
                    )
                }
            }
            .launchIn(viewModelScope)
    }

}