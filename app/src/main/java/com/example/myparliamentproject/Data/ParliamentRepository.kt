package com.example.myparliamentproject.Data

import kotlinx.coroutines.coroutineScope

/* Jani Salo
   ID: 2013109
   pvm: 24.9.2021
 */

class ParliamentRepository(private val parliamentDao: ParliamentDao) {

    //Gets all members from database
    fun getParliamentMembers() = parliamentDao.getAll()

    //Gets one member from database
    fun getMemberWithMemberNumber(personNumber: Int) = parliamentDao.getPersonByPersonNumber(personNumber)

    //get members from one party from database
    fun getMembersWithParty(partyName: String) = parliamentDao.getPersonsByParty(partyName)


    //Fetches all members from api
    suspend fun getParliamentFromApi() {
        coroutineScope {
            val getFromNetWork = ParliamentMemberApi.retrofitService.getMembers()
            getFromNetWork?.forEach { item ->
                ParliamentDataBase.getDataBase().parliamentDao().insert(item)

            }
        }
    }
}