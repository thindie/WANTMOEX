package com.example.cryptoview.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.example.core.network.apiservice.ApiService
import com.example.cryptoview.application.di.AppComponent
import javax.inject.Inject
import kotlinx.coroutines.launch

class ActivityMain : ComponentActivity() {

    @Inject
    lateinit var service: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        initDaggerComponent()

        lifecycleScope.launch {
            val result = service.getTopCoinsEURO(limit = 10).getPopulated()
            val result1 = service.getTopCoinsRUB(limit = 10).getPopulated()
        }
    }

    private fun initDaggerComponent() {
        AppComponent.init(applicationContext).inject(this)
    }
}
