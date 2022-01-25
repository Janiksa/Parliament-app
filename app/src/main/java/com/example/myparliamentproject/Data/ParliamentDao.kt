package com.example.myparliamentproject.Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/* Jani Salo
   ID: 2013109
   pvm: 24.9.2021
 */

@Dao
interface ParliamentDao {

    //Inserts parliament member to database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(parliamentMember: ParliamentMember)

    //Returns all parliament members from room database
    @Query("SELECT * FROM member_table ORDER BY first ASC")
    fun getAll(): LiveData<List<ParliamentMember>>

    //Returns one parliament member, person number is used to tell
    //which parliament member is wanted
    @Query("SELECT * FROM member_table WHERE personNumber = :personNumber")
    fun getPersonByPersonNumber(personNumber: Int): LiveData<List<ParliamentMember>>

    //returns all members from one party
    @Query("SELECT * FROM member_table WHERE party = :wantedParty")
    fun getPersonsByParty(wantedParty: String): LiveData<List<ParliamentMember>>

}