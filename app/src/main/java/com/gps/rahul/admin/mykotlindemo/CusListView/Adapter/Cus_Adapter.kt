package com.gps.rahul.admin.mykotlindemo.CusListView.Adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.gps.rahul.admin.mykotlindemo.R

class Cus_Adapter(private val activity: Activity, val ss: Array<String>,val img: Array<Int>) : BaseAdapter() {
    /*private var ss:Array<String>
    //private var img:Array<Int> ?= null
    val img:Array<Int> = img

    init {
        this.ss=ss as Array<String>
        //this.img=img as Array<Int>
    }*/

    override fun getCount(): Int {
        return ss.size
    }

    override fun getItem(i: Int): Any {
        return i
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    @SuppressLint("InflateParams", "ViewHolder")
    override fun getView(i: Int, convertView: View?, viewGroup: ViewGroup?): View {
        var vi: View =View.inflate(activity,R.layout.cus_listview, null)
        val title = vi.findViewById<TextView>(R.id.ctxt_name)
        val image= vi.findViewById<ImageView>(R.id.image)
        title.text = ss[i]
        image.setImageResource(img[i])
        //title.setText(ss[i])
        return vi
    }
}