package com.printzero.osslib

import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Build
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_libs.view.*

class LibAdapter(private val libs: MutableList<Lib>, private val daskMode: Boolean = false) : RecyclerView.Adapter<LibAdapter.TestHolder>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibAdapter.TestHolder {
        val inflatedView = parent.inflate(R.layout.row_libs, false)
        return TestHolder(inflatedView)

    }

    override fun getItemCount() = libs.size

    override fun onBindViewHolder(holder: LibAdapter.TestHolder, position: Int) {
        holder.initRow(libs[position], this.daskMode)
    }

    class TestHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var view: View = v

        fun initRow(lib: Lib, daskMode: Boolean) {
            view.lib_name.text = lib.name
            view.lib_desc.text = lib.description
            view.license_chip.text = lib.license.spdx_id
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
                val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(lib.license.url))
                view.context.startActivity(webIntent)
            }

            if (lib.readme.isEmpty()) {
                view.readme_chip.visibility = View.INVISIBLE
            } else {

                view.readme_chip.setOnClickListener {
                    Log.d(javaClass.name, "readme clicked")
                }
                view.readme_chip.text = "README"
            }

            view.setOnClickListener {
                val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/${lib.name}"))
                view.context.startActivity(webIntent)
            }


            if (daskMode) {
                view.readme_chip.chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(view.context, R.color.osslib_dark_chip_bg))
                view.license_chip.chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(view.context, R.color.osslib_dark_chip_bg))
                view.readme_chip.setTextColor(ContextCompat.getColor(view.context, R.color.dim_white))
                view.license_chip.setTextColor(ContextCompat.getColor(view.context, R.color.dim_white))

                view.lib_name.setTextColor(ContextCompat.getColor(view.context, android.R.color.white))
                view.lib_desc.setTextColor(ContextCompat.getColor(view.context, android.R.color.white))
            }
        }
    }
}