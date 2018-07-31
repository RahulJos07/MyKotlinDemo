package com.gps.rahul.admin.mykotlindemo.CusListView

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.gps.rahul.admin.mykotlindemo.CusListView.Adapter.Cus_Adapter
import com.gps.rahul.admin.mykotlindemo.R

class Cus_ListView : AppCompatActivity() {
    var cus_ListView:ListView?=null
    var ss= arrayOf("Android","Java","Python","Php")
    var adapter:Cus_Adapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cus__list_view)
        cus_ListView= findViewById<ListView>(R.id.cus_list_view)
        adapter= Cus_Adapter(this,ss)
        (cus_ListView as ListView).adapter=adapter
    }
}
