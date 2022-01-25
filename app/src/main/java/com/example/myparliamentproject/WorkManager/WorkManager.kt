package com.example.myparliamentproject.WorkManager

import android.content.Context
import androidx.work.*
import com.example.myparliamentproject.Data.ParliamentDataBase
import com.example.myparliamentproject.Data.ParliamentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

/* Jani Salo
   ID: 2013109
   pvm: 5.10.2021
 */

//Class for updating database once a day
class ApiWorkManager(appContext: Context, workerParams: WorkerParameters):
    CoroutineWorker(appContext, workerParams){

    val dao = ParliamentDataBase.getDataBase().parliamentDao()
    val repo = ParliamentRepository(dao)

    companion object{
        const val WORK_NAME = "com.example.myparliamentproject.WorkManager.ApiWorkManager"
    }

        override suspend fun doWork(): Result {
            try {
                updateDataBase()
            }
            catch (e: Exception){
                return Result.retry()
            }

            return Result.success()
        }

    private fun updateDataBase() {
        CoroutineScope(Dispatchers.IO).launch {
            repo.getParliamentFromApi()
        }

    }
}