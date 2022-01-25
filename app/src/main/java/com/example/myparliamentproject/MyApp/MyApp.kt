package com.example.myparliamentproject.MyApp

import android.app.Application
import android.content.Context


/* Jani Salo
   ID: 2013109
   pvm: 24.9.2021
 */

/*
    MyApp purpose is to get context
    when needed
 */
class MyApp : Application() {



    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        }
}