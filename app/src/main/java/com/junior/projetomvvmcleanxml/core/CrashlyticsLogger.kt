package com.junior.projetomvvmcleanxml.core

import com.google.firebase.crashlytics.FirebaseCrashlytics

object CrashlyticsLogger {
    private val crashlytics = FirebaseCrashlytics.getInstance()
   // registrar eventos normais
    fun logMessage(message: String){
        crashlytics.log(message)
    }
    //reportar uma excesao manualmente
    fun logCrash(e: Throwable){
        crashlytics.recordException(e)
    }
    //usado para identificar quem sofreu o crash
    fun setUserId(userId: String){
        crashlytics.setUserId(userId)
    }
    //chaves personalizadas
    fun setCustomKey(key: String, value: String){
        crashlytics.setCustomKey(key, value)
    }
}