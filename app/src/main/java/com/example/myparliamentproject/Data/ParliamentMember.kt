package com.example.myparliamentproject.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

/* Jani Salo
   ID: 2013109
   pvm: 24.9.2021
 */

@Entity(tableName = "member_table")
data class ParliamentMember(
    @PrimaryKey(autoGenerate = true)
    val personNumber: Int,
    val seatNumber: Int = 0,
    val last: String,
    val first: String,
    val party: String,
    val minister: Boolean = false,
    val picture: String = "",
    val twitter: String = "",
    val bornYear: Int = 0,
    val constituency: String = "")
{
}