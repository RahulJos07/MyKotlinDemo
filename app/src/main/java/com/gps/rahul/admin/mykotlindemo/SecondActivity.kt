package com.gps.rahul.admin.mykotlindemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class SecondActivity : AppCompatActivity() {
    var txtname: TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        txtname= findViewById<TextView>(R.id.txtname) as TextView
        val ss:String = intent.getStringExtra("RJ")
        txtname!!.setText(ss);
    }
}
