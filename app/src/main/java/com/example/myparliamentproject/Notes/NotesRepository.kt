package com.example.myparliamentproject.Notes

import androidx.lifecycle.LiveData
import com.example.myparliamentproject.Data.ParliamentDataBase
import com.example.myparliamentproject.Data.ParliamentMemberApi
import kotlinx.coroutines.coroutineScope

/* Jani Salo
   ID: 2013109
   pvm: 2.10.2021
 */

class NotesRepository(private val notesDao: NotesDao) {

    //returns all the notes from database
    fun getAllNotes(personNumber: Int) = notesDao.getNotes(personNumber)

    //returns score from database
    fun getScore(personNumber: Int): LiveData<Int> = notesDao.getScore(personNumber)

    //returns score as integer
    fun getScoreInt(personNumber: Int): Int = notesDao.getScoreInt(personNumber)

    //returns notes from particular parliament member
    //parliament members person number is used as argument
    fun getNote(personNumber: Int) = notesDao.getNote(personNumber)

    //Function that inserts new notes of parliament members to database
    suspend fun insertNotes(notes: Notes) {
            notesDao.insertNotes(notes)
    }

    suspend fun insertScore(score: Score){
        coroutineScope {
            notesDao.insertScore(score)
        }
    }
}