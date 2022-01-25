package com.example.myparliamentproject.MemberNoteFragment


import androidx.lifecycle.ViewModel
import com.example.myparliamentproject.R

/* Jani Salo
   ID: 2013109
   pvm: 23.9.2021
 */

class MemberNoteViewModel : ViewModel() {

    //list of party images for recyclerview
    val partyImages = listOf(
        R.drawable.ic_kdp,
        R.drawable.ic_kesk,
        R.drawable.ic_kok,
        R.drawable.ic_liik,
        R.drawable.ic_ps,
        R.drawable.ic_rkp,
        R.drawable.ic_sdp,
        R.drawable.ic_vas,
        R.drawable.ic_vihr
    )
}