package com.example.rickandmorty


import com.example.example.Data.characterdata
import com.example.rickandmorty.Data.locationdata
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface API {

    @GET("location/1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20")
    fun getdata():Call<List<locationdata>>
    @GET("character/{id}")
    fun getcharacter(@Path("id", encoded = false) id:List<String>):Call<List<characterdata>>
    @GET("character/{id}")
    fun getsinglecharacter(@Path("id",encoded = false) id: String?):Call<characterdata>
}