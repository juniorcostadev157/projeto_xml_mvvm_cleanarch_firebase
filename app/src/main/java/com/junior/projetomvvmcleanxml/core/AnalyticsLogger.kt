package com.junior.projetomvvmcleanxml.core


import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.junior.projetomvvmcleanxml.App

object AnalyticsLogger {

    private val analytics: FirebaseAnalytics by lazy {
        FirebaseAnalytics.getInstance(App.instance )
    }


    fun logEvent(eventName: String){
        analytics.logEvent(eventName, null)
    }
    // eventos com parametros
    fun logEvent(eventName: String, params: Map<String, String>){
        val bundle = Bundle()
            params.forEach { (key, value)->
                bundle.putString(key, value)
            }
        analytics.logEvent(eventName, bundle)


    }

    fun setUserProperty(name:String, value: String){
        analytics.setUserProperty(name, value)
    }

    fun setUserId(userID: String){
        analytics.setUserId(userID)
    }

}