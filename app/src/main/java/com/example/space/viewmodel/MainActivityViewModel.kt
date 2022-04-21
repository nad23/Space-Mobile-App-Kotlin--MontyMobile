package com.example.space.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.space.models.RecyclerData
import com.example.space.models.Rocket
//import com.example.space.models.RecyclerList
import com.example.space.network.RetroInstance
import com.example.space.network.RetroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Response
import java.util.*
import kotlin.collections.ArrayList

class MainActivityViewModel:ViewModel() {

    lateinit var recyclerListLiveData :MutableLiveData<ArrayList<RecyclerData>>


    private val _rocketLiveData = MutableLiveData<Rocket>()
    val rocketLiveData: LiveData<Rocket> = _rocketLiveData




    init {
        recyclerListLiveData= MutableLiveData()
    }

    fun getRecyclerListObserver():MutableLiveData<ArrayList<RecyclerData>>{
        return  recyclerListLiveData

    }

    fun makeApiCall(){

        viewModelScope.launch(Dispatchers.IO) {
          val retroInstance=  RetroInstance.getRetroInstance().create(RetroService::class.java)
           val response= retroInstance.getDataFromApi("true")
            Log.i("khi",response.toString())
            recyclerListLiveData.postValue(response)

        }
    }

    fun makeApiCallRocket( id:String)  {

        viewModelScope.launch(Dispatchers.IO) {
            val retroInt = RetroInstance.getRetroInstanceRocket().create(RetroService::class.java)
          val response = retroInt.getAllRockets(id)

          Log.i("kdjkd",response.toString())
          _rocketLiveData.postValue(response)

               }
        }

    }


