package com.example.thindie.wantmoex.di

import com.example.thindie.wantmoex.presentation.MainActivity
import dagger.Component

@Component(modules = [WMModule::class, WMModuleClass::class])
interface WMComponent {

    fun inject(activity: MainActivity)

}