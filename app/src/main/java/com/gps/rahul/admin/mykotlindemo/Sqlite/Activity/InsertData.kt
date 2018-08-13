package com.gps.rahul.admin.mykotlindemo.Sqlite.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.gps.rahul.admin.mykotlindemo.R
import com.gps.rahul.admin.mykotlindemo.Sqlite.Helper.DatabaseHelper
import com.gps.rahul.admin.mykotlindemo.Sqlite.Model.UserModel
import java.text.SimpleDateFormat
import java.util.*

class InsertData : AppCompatActivity() {
    lateinit var name_edt:EditText
    lateinit var email_edt:EditText
    lateinit var mobile_no_edt:EditText
    lateinit var radio_group_male_female:RadioGroup
    lateinit var rb:RadioButton
    lateinit var standard_class:Spinner
    lateinit var databaseHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_data)
        name_edt=findViewById(R.id.name_edt)
        email_edt=findViewById(R.id.email_edt)
        mobile_no_edt=findViewById(R.id.mobile_no_edt)
        radio_group_male_female=findViewById(R.id.radio_group_male_female)
        standard_class=findViewById(R.id.standard_class)
        databaseHelper= DatabaseHelper(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.save_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id=radio_group_male_female.getCheckedRadioButtonId()
        rb=findViewById(id)

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())

        when(item!!.itemId)
        {
            R.id.save_menu->
            {
                if(name_edt.text.toString().isEmpty())
                {
                    Toast.makeText(applicationContext,"Enter the Name",Toast.LENGTH_SHORT).show()
                }
                else if(email_edt.text.toString().isEmpty())
                {
                    Toast.makeText(applicationContext,"Enter the Email Id",Toast.LENGTH_SHORT).show()
                }
                else if(mobile_no_edt.text.toString().isEmpty())
                {
                    Toast.makeText(applicationContext,"Enter the Mobile Number",Toast.LENGTH_SHORT).show()
                }
                else if(standard_class.selectedItem.toString().equals("Select Standard"))
                {
                    Toast.makeText(applicationContext,"Please Select Standard",Toast.LENGTH_SHORT).show()
                }
                else {
                    val userModel= UserModel()
                    userModel.setName(name_edt.text.toString())
                    userModel.setEmail_Id(email_edt.text.toString())
                    userModel.setMobile_No(mobile_no_edt.text.toString())
                    userModel.setGender(rb.text.toString())
                    userModel.setClass(standard_class.selectedItem.toString())
                    userModel.setDateTime(currentDate)
                    var result:Boolean=databaseHelper.onStoreData(userModel)
                    when{
                        result->
                        {
                            Toast.makeText(applicationContext,"Data Inserted Successfully",Toast.LENGTH_SHORT).show()
                            var save_Intent = Intent(applicationContext, ListGettingData::class.java)
                            startActivity(save_Intent)
                        }
                        else->Toast.makeText(applicationContext,"Failed To Insert Data",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
