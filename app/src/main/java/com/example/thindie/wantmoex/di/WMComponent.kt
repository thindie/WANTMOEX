package com.example.thindie.wantmoex.di

import com.example.thindie.wantmoex.presentation.MainActivity
import com.example.thindie.wantmoex.presentation.SharesViewModel
import dagger.Component

@Component(modules = [WMModule::class, WMModuleClass::class])
interface WMComponent {

    fun inject(activity: MainActivity)
    fun inject(sharesViewModel: SharesViewModel)
}