package com.example.space.models

import java.util.*
import kotlin.collections.ArrayList


data class RecyclerData (
        val rocket :String,
        val success :String,
        val details:String ,
        val name :String ,
        val date_local :Date,
        val upcoming :Boolean,
        val id :String,
         val links :Links,
        )
data class Links (val patch:Patch)
data class Patch( val small:String ,val large:String)