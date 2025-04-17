package com.example.demonav.ui.main.detail

import com.example.demonav.data.model.UserInfo
import com.example.demonav.ui.navigate.args.DetailArgs
import com.example.demonav.ui.navigate.args.UpdateArgs
import com.example.demonav.ui.navigate.result.UpdateResult

sealed interface DetailAction {
    data class UpdateUserInfo(val result: UpdateResult) : DetailAction
    data object LoadUserInfo : DetailAction
}

sealed interface DetailEvent {
    data object LoadError : DetailEvent
}

data class DetailState(
    val userId: String,
    val isLoading: Boolean = false,
    val userInfo: UserInfo? = null,
)

fun DetailArgs?.toState() = DetailState(
    userId = this?.userId.orEmpty(),
    userInfo = UserInfo(
        name = this?.userName.orEmpty(),
        email = ""
    )
)

fun DetailState.toUpdateArgss() = UpdateArgs(
    userId = userId,
    userName = userInfo?.name.orEmpty(),
    email = userInfo?.email.orEmpty()
)

