package com.example.demonav.ui.main.update

import com.example.demonav.data.model.UserInfo
import com.example.demonav.ui.navigate.args.UpdateArgs
import com.example.demonav.ui.navigate.result.UpdateResult

sealed interface UpdateAction {
    data object UpdateUserInfo : UpdateAction
}

sealed interface UpdateEvent {
    data object UpdateSuccess : UpdateEvent
}

data class UpdateState(
    val userId: String,
    val isLoading: Boolean = false,
    val userInfo: UserInfo? = null,
)

fun UpdateArgs?.toState() = UpdateState(
    userId = this?.userId.orEmpty(),
    userInfo = UserInfo(
        name = this?.userName.orEmpty(),
        email = this?.email.orEmpty()
    )
)

fun UpdateState.setFragmentResult() = UpdateResult(
    name = "Nguyen Van AAAA", email = "dadsjjask@gmail.com"
)

