package com.example.thindie.wantmoex.di

import dagger.Component

@Component(modules = [WMModule::class, WMModuleClass::class])
interface WMComponent {


}