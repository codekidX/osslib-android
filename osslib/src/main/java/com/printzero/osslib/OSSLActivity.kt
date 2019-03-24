package com.printzero.osslib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_ossl.*
import kotlinx.android.synthetic.main.row_libs.view.*
import java.io.*



class OSSLActivity : AppCompatActivity() {

    companion object {
        val LIBTYPE = object : TypeToken<MutableList<Lib>>() {}.type
    }

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: LibAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ossl)

        try {
            val osslJson = readJson()

            linearLayoutManager = LinearLayoutManager(this)
            libs_recycler_view.layoutManager = linearLayoutManager

            val gson = Gson()
            val libraries: MutableList<Lib> = gson.fromJson<MutableList<Lib>>(osslJson, LIBTYPE)
            adapter = LibAdapter(libraries)
            libs_recycler_view.adapter = adapter
        } catch (e: IOException) {
            Log.e(javaClass.name, "osslib.json not found !!")
        }



    }

    @Throws(IOException::class)
    private fun readJson(): Reader {
        val stream: InputStream = assets.open("osslib.json")
        return BufferedReader(InputStreamReader(stream))
    }
}
