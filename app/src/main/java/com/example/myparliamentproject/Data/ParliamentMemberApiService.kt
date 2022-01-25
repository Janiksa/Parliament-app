package com.example.myparliamentproject.Data


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

/* Jani Salo
   ID: 2013109
   pvm: 25.9.2021
 */

private const val BASE_URL = "https://users.metropolia.fi/~peterh/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ParliamentMemberApiService {
    @GET("mps.json")
    suspend fun getMembers():
            List<ParliamentMember>?

}
object ParliamentMemberApi {
    val retrofitService: ParliamentMemberApiService by lazy {
        retrofit.create(ParliamentMemberApiService::class.java)
    }
}