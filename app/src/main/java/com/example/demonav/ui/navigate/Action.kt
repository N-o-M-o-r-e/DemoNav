package com.example.demonav.ui.navigate

import com.example.demonav.ui.navigate.args.DetailArgs
import com.example.demonav.ui.navigate.args.UpdateArgs

sealed interface Action {
    data class HomeToDetail(val args: DetailArgs) : Action
    data object HomeToSetting : Action
    data class DetailToUpdate(val args: UpdateArgs): Action
    data object UpdatePopToHome : Action
}