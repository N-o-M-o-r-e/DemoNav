package com.example.demonav.ui.navigate

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.demonav.R
import jakarta.inject.Inject

class MainNavigateActionImpl @Inject constructor(
    private val activity: Activity
) : MainNavigateAction {

    private val navController: NavController by lazy {
        NavHostFragment.findNavController(
            (activity as FragmentActivity).supportFragmentManager.findFragmentById(
                R.id.nav_host_main_activity
            )!!
        )
    }

    override fun mainNavController(): NavController {
        return navController
    }

    override fun popBackStack(): Boolean {
        return navController.popBackStack()
    }

    override fun navigate(action: Action): Boolean {
        return kotlin.runCatching {
            navController.navigate(action.toDirection())
            true
        }.getOrElse {
            it.printStackTrace()
            false
        }
    }
}
