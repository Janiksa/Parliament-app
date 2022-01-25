package com.example.myparliamentproject.Notes

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/* Jani Salo
   ID: 2013109
   pvm: 2.10.2021
 */

@Dao
interface NotesDao {

    //Inserts new notes to database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(notes: Notes)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScore(score: Score)

    //Returns persons score and notes
    @Query("SELECT * FROM note_table WHERE personNumber = :personNumber")
    fun getNotes(personNumber: Int): LiveData<List<Notes>>

    //Returns note(s) from particular member, which is searched by using person number
    @Query("SELECT note FROM note_table WHERE personNumber = :personNumber")
    fun getNote(personNumber: Int): String

    //Returns score for particular member, which is searched by using person number
    @Query("SELECT score FROM score_table WHERE personNumber = :personNumber")
    fun getScore(personNumber: Int): LiveData<Int>

    //Gets raw integer from database, so score can be used to calculate new score
    @Query("SELECT score FROM score_table WHERE personNumber = :personNumber")
    fun getScoreInt(personNumber: Int): Int
}