package com.printzero.osslib

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_libs.view.*

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
        //2
        private var view: View = v
        private var photo: Lib? = null

        //3
        init {
            v.setOnClickListener(this)
        }

        fun initRow(test: Lib) {
            view.lib_name.text = test.name
            view.lib_desc.text = test.description
        }

        //4
        override fun onClick(v: View) {
            Log.d("RecyclerView", "CLICK!")
        }

        companion object {
            //5
            private val PHOTO_KEY = "PHOTO"
        }
    }
}