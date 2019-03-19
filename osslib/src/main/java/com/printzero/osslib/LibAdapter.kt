package com.printzero.osslib

import android.content.res.ColorStateList
import android.os.Build
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_libs.view.*
import java.util.*

/**
 * Copyright 2017 codekid for OSSLibDemo. Do not use any
 * of the code befor asking my permission.
 */
class LibAdapter(private val libs: MutableList<Lib>) : RecyclerView.Adapter<LibAdapter.TestHolder>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibAdapter.TestHolder {
        val inflatedView = parent.inflate(R.layout.row_libs, false)
        return TestHolder(inflatedView)

    }

    override fun getItemCount() = libs.size

    override fun onBindViewHolder(holder: LibAdapter.TestHolder, position: Int) {
        holder.initRow(libs[position])
    }

    class TestHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private var view: View = v

        init {
            v.setOnClickListener(this)
        }

        fun initRow(test: Lib) {
            view.lib_name.text = test.name
            view.lib_desc.text = test.description
            view.license_chip.text = test.license.spdx_id
            val iconColors = view.context.resources.getIntArray(R.array.project_img_color)
            val bgColors = view.context.resources.getIntArray(R.array.project_bg_color)
            val randomColorIndex = (Math.random() * iconColors.size).toInt()

            view.icon_bg.setCardBackgroundColor(bgColors[randomColorIndex])
            view.icon_bg.strokeColor = iconColors[randomColorIndex]
            view.icon_bg.strokeWidth = 1
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                view.icon_img.imageTintList = ColorStateList.valueOf(iconColors[randomColorIndex])
            } else {
                view.icon_img.setColorFilter(ContextCompat.getColor(view.context, iconColors[randomColorIndex]), android.graphics.PorterDuff.Mode.SRC_IN)
            }

            view.license_chip.setOnClickListener {
                Log.d(javaClass.name, "readme clicked")
            }

            if (test.readme.isEmpty()) {
                view.readme_chip.visibility = View.INVISIBLE
            } else {
                view.readme_chip.setOnClickListener {
                    Log.d(javaClass.name, "readme clicked")
                }
                view.readme_chip.text = "README"
            }
        }


        override fun onClick(v: View) {
            Log.d("RecyclerView", "CLICK!")
        }
    }
}