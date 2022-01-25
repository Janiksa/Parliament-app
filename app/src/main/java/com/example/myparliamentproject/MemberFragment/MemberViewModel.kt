package com.example.myparliamentproject.MemberFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.myparliamentproject.Data.ParliamentDataBase
import com.example.myparliamentproject.Data.ParliamentMember
import com.example.myparliamentproject.Data.ParliamentRepository
import com.example.myparliamentproject.Notes.NotesRepository
import com.example.myparliamentproject.Notes.Score

/* Jani Salo
   ID: 2013109
   pvm: 23.9.2021
 */

class MemberViewModel : ViewModel() {


    private val dao = ParliamentDataBase.getDataBase().parliamentDao()
    private val repo = ParliamentRepository(dao)
    private val notedao = ParliamentDataBase.getDataBase().notesDao()
    private val noterepo = NotesRepository(notedao)

    //Fetches member score from database
    //person number is used to find right person
    fun getScore(personNumber: Int): LiveData<Int>{
       return noterepo.getScore(personNumber)
    }

    //Loads one member from database
    //person number is used to find right person
    fun loadOneMember(personNumber: Int): LiveData<List<ParliamentMember>>{
        return repo.getMemberWithMemberNumber(personNumber)
    }

    //inserts score to database
    suspend fun insertScore(score: Score){
        noterepo.insertScore(score)
    }

    //Fetches member score as integer from database, so it can be used
    //to calculate new score if it is changed
    fun getScoreInt(personNumber: Int): Int {
        return noterepo.getScoreInt(personNumber)
    }




}