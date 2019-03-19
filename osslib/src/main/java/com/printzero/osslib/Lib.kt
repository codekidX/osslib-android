package com.printzero.osslib

/**
 * Copyright 2017 codekid for OSSLibDemo. Do not use any
 * of the code befor asking my permission.
 */
data class Lib(val name:String, val description: String, val creator: String, val readme: String, val license: License)
data class License(val key:String, val name: String, val spdx_id: String, val url: String)