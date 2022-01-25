package com.example.myparliamentproject.PartyFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myparliamentproject.Data.ParliamentDataBase
import com.example.myparliamentproject.Data.ParliamentMember
import com.example.myparliamentproject.Data.ParliamentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/* Jani Salo
   ID: 2013109
   pvm: 23.9.2021
 */

class PartyViewModel : ViewModel() {

    //variables for Parliament repository and parliament dao
    private val dao = ParliamentDataBase.getDataBase().parliamentDao()
    private val repo = ParliamentRepository(dao)

    //Function that returns all the parliament members from database
    fun loadMembers(): LiveData<List<ParliamentMember>> {
        return repo.getParliamentMembers()
    }

    //Function to fetch parliament members from particular party
    fun loadPartyMembers(partyName: String): LiveData<List<ParliamentMember>> {
        return repo.getMembersWithParty(partyName)
    }
}