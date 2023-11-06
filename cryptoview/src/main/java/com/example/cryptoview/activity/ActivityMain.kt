package com.example.cryptoview.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import com.example.cryptoview.application.di.AppComponent
import com.example.cryptoview.application.viewmodelfactory.ViewModelFactory
import javax.inject.Inject

class ActivityMain : ComponentActivity() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val mainViewModel: MainViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        initDaggerComponent()
    }

    private fun initDaggerComponent() {
        AppComponent.init(applicationContext).inject(this)
    }
}
