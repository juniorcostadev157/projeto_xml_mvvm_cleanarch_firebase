package com.junior.projetomvvmcleanxml.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.junior.projetomvvmcleanxml.data.repository.ItemRepositoryImpl

class CustomWorkerFactory(
    private val itemRepository: ItemRepositoryImpl
): WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {

        return when(workerClassName) {
            SyncItemWorker::class.java.name -> SyncItemWorker(
                appContext,
                workerParameters,
                itemRepository
            )

            else -> null
        }
    }

}