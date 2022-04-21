package com.example.space.network

import com.example.space.models.RecyclerData
import com.example.space.models.Rocket
import retrofit2.Call
//import com.example.space.models.RecyclerList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*
import kotlin.collections.ArrayList

interface RetroService {

    @GET("launches?")
    suspend fun getDataFromApi(@Query("success") success:String):ArrayList<RecyclerData>

    @GET("{id}/")
    suspend fun getAllRockets(@Path("id") Id: String):Rocket

}
