package com.junior.projetomvvmcleanxml

import android.app.Application
import android.util.Log
import androidx.work.Configuration
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.junior.projetomvvmcleanxml.presentation.utils.InjectContainer
import com.junior.projetomvvmcleanxml.worker.CustomWorkerFactory
import com.junior.projetomvvmcleanxml.worker.SyncItemWorker
import java.util.concurrent.TimeUnit

class App: Application(), Configuration.Provider {
    companion object{
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        InjectContainer.init(this)
        WorkManager.initialize(this, workManagerConfiguration)
        scheduleSyncWorker()
    }

    private fun scheduleSyncWorker(){
        val request = PeriodicWorkRequestBuilder<SyncItemWorker>(5, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "sync_items",
            ExistingPeriodicWorkPolicy.KEEP,
            request
        )
    }

    private val customWorkerFactory by lazy {
        CustomWorkerFactory(InjectContainer.itemRepository)
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setMinimumLoggingLevel(Log.INFO)
            .setWorkerFactory(customWorkerFactory)
            .build()


}