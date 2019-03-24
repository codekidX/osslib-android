package com.printzero.osslib

data class Lib(
    val name:String,
    val description: String,
    val creator: String,
    val readme: String,
    val license: License,
    val license_exp: String)

data class License(val key:String, val name: String, val spdx_id: String, val url: String)