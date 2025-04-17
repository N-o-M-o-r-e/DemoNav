package com.example.demonav.ui.navigate

import com.example.demonav.ui.main.detail.DetailFragmentDirections
import com.example.demonav.ui.main.home.HomeContainerFragmentDirections
import com.example.demonav.ui.main.update.UpdateFragmentDirections

fun Action.toDirection() = when (this) {
    is Action.HomeToDetail -> HomeContainerFragmentDirections.homeToDetail(args)
    is Action.HomeToSetting -> HomeContainerFragmentDirections.homeToSetting()
    is Action.DetailToUpdate -> DetailFragmentDirections.detailToUpdate(args)
    is Action.UpdatePopToHome -> UpdateFragmentDirections.updatePopToHome()
}

