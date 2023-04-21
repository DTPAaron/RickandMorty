package com.example.example.Data

import com.example.rickandmorty.Data.Location
import com.example.rickandmorty.Data.Origin

data class characterdata (

    var id       : Int      = 0,
    var name     : String?   = null,
    var status   : String?   = null,
    var species  : String?   = null,
    var type     : String?   = null,
    var gender   : String?   = null,
    var origin   : Origin?   = Origin(),
    var location : Location? = Location(),
    var image    : String?   = null,
    var episode  : List<String>   = arrayListOf(),
    var url      : String?   = null,
    var created  : String?   = null

)
