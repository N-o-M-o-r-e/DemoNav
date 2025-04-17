package com.example.demonav.ui.navigate

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
interface NavigateModule {

    @Binds
    @ActivityScoped
    fun bindMainNavigateAction(mainNavigateActionImpl: MainNavigateActionImpl): MainNavigateAction
}
