package com.example.myparliamentproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.work.PeriodicWorkRequestBuilder
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.WorkManager
import com.example.myparliamentproject.MyApp.MyApp
import com.example.myparliamentproject.WorkManager.ApiWorkManager

/* Jani Salo
   ID: 2013109
   pvm: 23.9.2021
 */

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateDB()
    }

    //Calls the function once a day to update database of parliament members
    private fun updateDB() {
        CoroutineScope(Dispatchers.Default).launch {
            val workReq = PeriodicWorkRequestBuilder<ApiWorkManager>(1, TimeUnit.DAYS).build()
            WorkManager.getInstance(MyApp.appContext).enqueueUniquePeriodicWork(
                ApiWorkManager.WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                workReq)
            Log.d("DBupdated", "Database updated")
        }
    }
}
