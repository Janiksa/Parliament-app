package com.example.myparliamentproject.Notes

import androidx.room.Entity
import androidx.room.PrimaryKey

/* Jani Salo
   ID: 2013109
   pvm: 3.10.2021
 */

@Entity(tableName = "score_table")
data class Score(
    @PrimaryKey(autoGenerate = true)
    val personNumber: Int,
    val score: Int = 0
) {
}
