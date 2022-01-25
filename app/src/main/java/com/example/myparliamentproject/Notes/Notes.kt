package com.example.myparliamentproject.Notes

import androidx.room.Entity
import androidx.room.PrimaryKey

/* Jani Salo
   ID: 2013109
   pvm: 2.10.2021
 */

@Entity(tableName = "note_table")
data class Notes(
    val personNumber: Int,
    val note: String = "",
){
    @PrimaryKey(autoGenerate = true) var i = 0
}
