package com.example.rickandmorty.Data

data class locationdata  (
    var id        : Int?              = null,
    var name      : String?           = null,
    var type      : String?           = null,
    var dimension : String?           = null,
    var residents : List<String> = arrayListOf(),
    var url       : String?           = null,
    var created   : String?           = null) {
}