package com.gps.rahul.admin.mykotlindemo.RecyclerView

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.gps.rahul.admin.mykotlindemo.R
import com.gps.rahul.admin.mykotlindemo.RecyclerView.Adapter.Cus__Recyc_Adapter

class Recycler_View_Cus : AppCompatActivity() {
    lateinit var recycler_view: RecyclerView
    var ss= arrayOf("Android","Java","Python","Php")
    var img= arrayOf(R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler__view)
        recycler_view=findViewById<RecyclerView>(R.id.recycler_view) as RecyclerView
        recycler_view.layoutManager=LinearLayoutManager(this,LinearLayout.VERTICAL,false)
        recycler_view.adapter= Cus__Recyc_Adapter(this,ss,img)
    }
}
// In Recycler View can't take height match parent in custom layout Design xml file