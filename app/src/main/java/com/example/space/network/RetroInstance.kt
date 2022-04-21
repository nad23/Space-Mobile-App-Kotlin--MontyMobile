package com.example.space.network

import com.example.space.Constants.Companion.BASE_URL
import com.example.space.Constants.Companion.BASE_URL_Rocket
import com.example.space.models.RecyclerData

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class RetroInstance{

      companion object {
          fun getRetroInstance():Retrofit{
              return Retrofit.Builder()
                      .baseUrl(BASE_URL)
                      .addConverterFactory(GsonConverterFactory.create())
                      .build()

          }
          fun getRetroInstanceRocket():Retrofit{
              return Retrofit.Builder()
                      .baseUrl(BASE_URL_Rocket)
                      .addConverterFactory(GsonConverterFactory.create())
                      .build()

          }
      }
}