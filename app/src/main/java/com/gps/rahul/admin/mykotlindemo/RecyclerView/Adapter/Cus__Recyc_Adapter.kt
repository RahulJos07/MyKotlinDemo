package com.gps.rahul.admin.mykotlindemo.RecyclerView.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.gps.rahul.admin.mykotlindemo.R

class Cus__Recyc_Adapter(private val context: Context,private val ss: Array<String>,private val img: Array<Int>): RecyclerView.Adapter<Cus__Recyc_Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.cus_listview,parent,false))
    }

    override fun getItemCount(): Int {
        return ss.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.image?.setImageResource(img[position])
        holder?.ctxt_name?.text=ss[position]
    }

    class ViewHolder(v: View):RecyclerView.ViewHolder(v)
    {
        val image=v.findViewById<ImageView>(R.id.image) as ImageView
        val ctxt_name=v.findViewById<TextView>(R.id.ctxt_name) as TextView
    }
}