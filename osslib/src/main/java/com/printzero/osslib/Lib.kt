package com.printzero.osslib

data class Ossl(val projects: MutableList<Lib>, val credits: MutableList<Credit>)

data class Lib(
    val name:String,
    val description: String,
    val creator: String,
    val readme: String,
    val license: License,
    val license_exp: String)

data class License(val key:String, val name: String, val spdx_id: String, val url: String)

data class Credit(val name: String, val url: String, val alias: String, val image: String)