package com.printzero.osslibdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.printzero.osslib.OSSLActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.open_osslib).setOnClickListener {
            startActivity(Intent(this@MainActivity, OSSLActivity::class.java))
        }
    }
}
