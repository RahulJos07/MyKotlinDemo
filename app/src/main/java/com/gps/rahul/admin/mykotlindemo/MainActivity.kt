package com.gps.rahul.admin.mykotlindemo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var edtname: EditText?=null
    var button: Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtname= findViewById<EditText>(R.id.edtname) as EditText
        button= findViewById<Button>(R.id.btn) as Button
        button!!.setOnClickListener( View.OnClickListener {
            val ss:String = edtname!!.getText().toString();
            Toast.makeText(applicationContext,"Button Clicked",Toast.LENGTH_SHORT).show();
            var i=Intent(this,SecondActivity::class.java)
            i.putExtra("RJ", ss)
            startActivity(i)
        });
    }
}
