package com.example.cryptoview.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.view.WindowCompat
import com.example.core.network.apiservice.ApiService
import com.example.cryptoview.application.di.AppComponent
import javax.inject.Inject

class ActivityMain : ComponentActivity() {

    @Inject
    lateinit var service: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        initDaggerComponent()
    }

    private fun initDaggerComponent() {
        AppComponent.init(applicationContext).inject(this)
    }
}
