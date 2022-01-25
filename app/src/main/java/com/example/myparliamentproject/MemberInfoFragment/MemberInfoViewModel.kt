package com.example.myparliamentproject.MemberInfoFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.myparliamentproject.Data.ParliamentDataBase
import com.example.myparliamentproject.Data.ParliamentRepository
import com.example.myparliamentproject.Notes.Notes
import com.example.myparliamentproject.Notes.NotesRepository

/* Jani Salo
   ID: 2013109
   pvm: 23.9.2021
 */

class MemberInfoViewModel : ViewModel() {

    private val dao = ParliamentDataBase.getDataBase().parliamentDao()
    private val repo = ParliamentRepository(dao)

    private val notedao = ParliamentDataBase.getDataBase().notesDao()
    private val noteRepo = NotesRepository(notedao)

    //Loads notes from particular member
    //person number is used to find notes for right member
    fun loadNotes(personNumber: Int): LiveData<List<Notes>>{
        return noteRepo.getAllNotes(personNumber)
    }

    //puts new notes to database
    suspend fun insertNotes(notes: Notes){
        noteRepo.insertNotes(notes)
    }
}