package com.junior.projetomvvmcleanxml.worker

import android.Manifest
import android.content.Context
import androidx.annotation.RequiresPermission
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.junior.projetomvvmcleanxml.data.repository.ItemRepositoryImpl


class SyncItemWorker(
    context: Context,
    params: WorkerParameters,
    private val repository: ItemRepositoryImpl
): CoroutineWorker(context, params) {

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override suspend fun doWork(): Result {
        println("🔥 SyncItemWorker executando...")
        return try {
            repository.syncPendingItems()
            println("✅ Sincronização concluída sem erros.")
            WorkerNotificationHelper.showNotification(
                applicationContext,
                "Sincronização",
                "Itens sincronizados com sucesso!"
            )
            Result.success()
        } catch (e: Exception) {
            println("❌ Erro na sincronização: ${e.localizedMessage}")
            WorkerNotificationHelper.showNotification(
                applicationContext,
                "Sincronização",
                "Falha na sincronização: ${e.message}"
            )
            Result.retry()
        }
    }
}