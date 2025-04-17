package com.example.demonav.ui.navigate

import androidx.navigation.NavController

interface MainNavigateAction {

    fun mainNavController(): NavController

    fun popBackStack(): Boolean

    fun navigate(action: Action): Boolean
}


