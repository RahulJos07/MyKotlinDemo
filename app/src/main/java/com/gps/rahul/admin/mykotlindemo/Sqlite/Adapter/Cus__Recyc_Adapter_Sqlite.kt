package com.gps.rahul.admin.mykotlindemo.RecyclerView.Adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.gps.rahul.admin.mykotlindemo.R
import com.gps.rahul.admin.mykotlindemo.Sqlite.Helper.DatabaseHelper
import com.gps.rahul.admin.mykotlindemo.Sqlite.Activity.ListGettingData
import com.gps.rahul.admin.mykotlindemo.Sqlite.Activity.UpdateData
import com.gps.rahul.admin.mykotlindemo.Sqlite.Model.UserModel

class Cus__Recyc_Adapter_Sqlite(private val context: Context, private val usermodel: ArrayList<UserModel>): RecyclerView.Adapter<Cus__Recyc_Adapter_Sqlite.ViewHolder>() {
    lateinit var databaseHelper: DatabaseHelper
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.display_data_layout,parent,false))
    }

    override fun getItemCount(): Int {
        return usermodel.size
    }

    override fun onBindViewHolder(holder: ViewHolder?,position: Int) {
        holder?.txt_name?.text=usermodel.get(position).getName()
        holder?.txt_email?.text=usermodel.get(position).getEmail_Id()
        holder?.txt_mb_no?.text=usermodel.get(position).getMobile_No()
        holder?.txt_gender?.text=usermodel.get(position).getGender()
        holder?.txt_standard?.text=usermodel.get(position).getClass()
        holder?.txt_date_time?.text=usermodel.get(position).getDateTime()
        databaseHelper= DatabaseHelper(context)
        holder?.update_btn?.setOnClickListener(View.OnClickListener { view: View? ->
            var intent=Intent(context, UpdateData::class.java)
            intent.putExtra("id",usermodel.get(position).getId())
            intent.putExtra("name",usermodel.get(position).getName())
            intent.putExtra("email",usermodel.get(position).getEmail_Id())
            intent.putExtra("mobile",usermodel.get(position).getMobile_No())
            intent.putExtra("gender",usermodel.get(position).getGender())
            intent.putExtra("cls",usermodel.get(position).getClass())
            intent.putExtra("datetime",usermodel.get(position).getDateTime())
            context.startActivity(intent)
        })
        holder?.delete_btn?.setOnClickListener(View.OnClickListener { view: View? ->
            var result:Boolean=databaseHelper.onDeleteData(usermodel.get(position).getId())
            when {
                result ->
                {
                    Toast.makeText(context, "Data Delete Successfully", Toast.LENGTH_SHORT).show()
                    var intent= Intent(context, ListGettingData::class.java)
                    context.startActivity(intent)
                }
                else -> Toast.makeText(context, "Data Not Deleted", Toast.LENGTH_SHORT).show()
            }
        })
    }

    class ViewHolder(v: View):RecyclerView.ViewHolder(v)
    {
        val txt_name=v.findViewById<ImageView>(R.id.txt_name) as TextView
        val txt_email=v.findViewById<ImageView>(R.id.txt_email) as TextView
        val txt_mb_no=v.findViewById<ImageView>(R.id.txt_mb_no) as TextView
        val txt_gender=v.findViewById<ImageView>(R.id.txt_gender) as TextView
        val txt_standard=v.findViewById<ImageView>(R.id.txt_standard) as TextView
        val txt_date_time=v.findViewById<ImageView>(R.id.txt_date_time) as TextView
        val update_btn=v.findViewById<Button>(R.id.update_btn) as Button
        val delete_btn=v.findViewById<Button>(R.id.delete_btn) as Button
    }
}