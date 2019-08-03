package com.printzero.osslib

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_ossl.*
import java.io.*
import android.text.Spannable
import android.text.style.ForegroundColorSpan
import android.text.SpannableString





class OSSLActivity : AppCompatActivity() {

    companion object {
        val LIBTYPE = object : TypeToken<MutableList<Lib>>() {}.type
        val DARK_MODE = "dark_mode_opt"
    }

    private lateinit var linearLayoutManager: LinearLayoutManager
    private var adapter: LibAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ossl)

        this.setColors()

        try {
            val osslJson = readJson()

            linearLayoutManager = LinearLayoutManager(this)
            libs_recycler_view.layoutManager = linearLayoutManager

            val gson = Gson()
            val libraries: MutableList<Lib> = gson.fromJson<MutableList<Lib>>(osslJson, LIBTYPE)
            intent.extras?.let {
                adapter = LibAdapter(libraries, it.getBoolean(DARK_MODE))
            }

            if (adapter == null) {
                adapter = LibAdapter(libraries)
            }
            libs_recycler_view.adapter = adapter
        } catch (e: IOException) {
            Log.e(javaClass.name, "osslib.json not found !!")
        }



    }

    private fun setColors() {
        val b = intent.extras
        b?.let {
            if (b.getBoolean(DARK_MODE)) {
                this.supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(applicationContext, R.color.osslib_dark_toolbar_bg)))
                val text = SpannableString(supportActionBar?.title)
                text.setSpan(ForegroundColorSpan(Color.WHITE), 0, text.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                supportActionBar?.title = text
                libs_recycler_view.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.osslib_dark_bg))

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    this.window.decorView.systemUiVisibility = 0
                    this.window.statusBarColor = ContextCompat.getColor(applicationContext, R.color.osslib_dark_bg)
                }
            }
        }
    }

    @Throws(IOException::class)
    private fun readJson(): Reader {
        val stream: InputStream = assets.open("osslib.json")
        return BufferedReader(InputStreamReader(stream))
    }
}
