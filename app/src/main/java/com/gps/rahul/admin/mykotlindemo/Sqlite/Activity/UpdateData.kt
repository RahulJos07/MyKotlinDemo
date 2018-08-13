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

class UpdateData : AppCompatActivity() {
    lateinit var name_edt_update: EditText
    lateinit var email_edt_update: EditText
    lateinit var mobile_no_edt_update: EditText
    lateinit var radio_group_male_female_update: RadioGroup
    lateinit var rb: RadioButton
    lateinit var radio_button_male_update:RadioButton
    lateinit var radio_button_female_update:RadioButton
    lateinit var standard_class_update: Spinner
    lateinit var databaseHelper: DatabaseHelper
    var id_get=""
    var name=""
    var email=""
    var mobile=""
    var gender=""
    var cls=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_data)
        id_get=intent.getStringExtra("id");
        name=intent.getStringExtra("name");
        email=intent.getStringExtra("email");
        mobile=intent.getStringExtra("mobile");
        gender=intent.getStringExtra("gender");
        cls=intent.getStringExtra("cls");

        name_edt_update=findViewById(R.id.name_edt_update)
        email_edt_update=findViewById(R.id.email_edt_update)
        mobile_no_edt_update=findViewById(R.id.mobile_no_edt_update)
        radio_group_male_female_update=findViewById(R.id.radio_group_male_female_update)
        radio_button_male_update=findViewById(R.id.radio_button_male_update)
        radio_button_female_update=findViewById(R.id.radio_button_female_update)
        standard_class_update=findViewById(R.id.standard_class_update)
        databaseHelper= DatabaseHelper(this)

        name_edt_update.setText(name)
        email_edt_update.setText(email)
        mobile_no_edt_update.setText(mobile)
        if(gender.equals("Male"))
        {
            radio_button_male_update.isChecked=true
        }else
        {
            radio_button_female_update.isChecked=true
        }
        val standardarray=resources.getStringArray(R.array.Class)
        val adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,standardarray)
        standard_class_update.adapter=adapter
        if(cls!=null) {
            val spinnerPosition = adapter.getPosition(cls)
            standard_class_update.setSelection(spinnerPosition)
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.update_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id=radio_group_male_female_update.getCheckedRadioButtonId()
        rb=findViewById(id)

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())

        when(item!!.itemId)
        {
            R.id.update_menu->
            {
                if(name_edt_update.text.toString().isEmpty())
                {
                    Toast.makeText(applicationContext,"Enter the Name", Toast.LENGTH_SHORT).show()
                }
                else if(email_edt_update.text.toString().isEmpty())
                {
                    Toast.makeText(applicationContext,"Enter the Email Id", Toast.LENGTH_SHORT).show()
                }
                else if(mobile_no_edt_update.text.toString().isEmpty())
                {
                    Toast.makeText(applicationContext,"Enter the Mobile Number", Toast.LENGTH_SHORT).show()
                }
                else if(standard_class_update.selectedItem.toString().equals("Select Standard"))
                {
                    Toast.makeText(applicationContext,"Please Select Standard", Toast.LENGTH_SHORT).show()
                }
                else {
                    val userModel= UserModel()
                    userModel.setId(id_get.toString())
                    userModel.setName(name_edt_update.text.toString())
                    userModel.setEmail_Id(email_edt_update.text.toString())
                    userModel.setMobile_No(mobile_no_edt_update.text.toString())
                    userModel.setGender(rb.text.toString())
                    userModel.setClass(standard_class_update.selectedItem.toString())
                    userModel.setDateTime(currentDate)
                    var result: Boolean =databaseHelper.onUpdateData(userModel)
                    when{
                        result->
                        {
                            Toast.makeText(applicationContext,"Data Update Successfully", Toast.LENGTH_SHORT).show()
                            var save_Intent = Intent(applicationContext, ListGettingData::class.java)
                            startActivity(save_Intent)
                        }
                        else-> Toast.makeText(applicationContext,"Failed To Update Data", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
