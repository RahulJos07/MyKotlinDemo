package com.gps.rahul.admin.mykotlindemo.CusListView.Adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.gps.rahul.admin.mykotlindemo.R

class Cus_Adapter(private val activity: Activity, ss: Array<String>) : BaseAdapter() {
    private var ss:Array<String>

    init {
        this.ss=ss as Array<String>
    }

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
        title.text = ss[i]
        return vi
    }
}