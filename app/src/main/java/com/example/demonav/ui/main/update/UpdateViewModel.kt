package com.example.demonav.ui.main.update

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demonav.ui.navigate.args.UpdateArgs
import com.example.demonav.ui.navigate.args.getArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    // lay du lieu tu SavedStateHandle
    private val args: UpdateArgs? by lazy { savedStateHandle.getArgs() }

    private val state by lazy { MutableStateFlow(args.toState()) }

    val stateFlow by lazy { state.asStateFlow() }

    val stateValue by lazy { state.value }

    private val _action by lazy { MutableSharedFlow<UpdateAction>() }

    private inline fun <reified A : UpdateAction> action() = _action.filterIsInstance<A>()

    private val eventChannel by lazy { Channel<UpdateEvent>(Channel.UNLIMITED) }

    fun sendEvent(event: UpdateEvent) {
        eventChannel.trySend(event)
    }

    val eventFlow by lazy { eventChannel.receiveAsFlow() }

    fun dispatch(action: UpdateAction) {
        viewModelScope.launch {
            _action.emit(action)
        }
    }

    init {
        initHandleUpdate()
    }

    private fun initHandleUpdate() {
        action<UpdateAction.UpdateUserInfo>()
            .onEach {
                delay(1000)
                sendEvent(UpdateEvent.UpdateSuccess)
            }
            .launchIn(viewModelScope)
    }
}