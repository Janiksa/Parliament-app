package com.example.myparliamentproject.Data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myparliamentproject.MyApp.MyApp
import com.example.myparliamentproject.Notes.Notes
import com.example.myparliamentproject.Notes.NotesDao
import com.example.myparliamentproject.Notes.Score

/* Jani Salo
   ID: 2013109
   pvm: 24.9.2021
 */

@Database(entities = [ParliamentMember::class, Notes::class, Score::class], version = 11, exportSchema = false)

abstract class ParliamentDataBase : RoomDatabase() {
    abstract fun parliamentDao(): ParliamentDao
    abstract fun notesDao(): NotesDao

    companion object {
        @Volatile
        private var INSTANCE: ParliamentDataBase? = null


        fun getDataBase(): ParliamentDataBase {
            return INSTANCE ?: synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        MyApp.appContext,
                        ParliamentDataBase::class.java, "Parliament_Member_Database"
                    )
                        .fallbackToDestructiveMigration().build()
                    INSTANCE = instance

                }
                return instance
            }

        }

    }
}