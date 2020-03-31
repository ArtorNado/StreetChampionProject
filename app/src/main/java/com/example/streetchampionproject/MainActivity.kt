package com.example.streetchampionproject

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.registration.*
import kotlinx.android.synthetic.main.sign_in.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in)
   /*     val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        btn_logIn.setOnClickListener {
            *//*val builder : MaterialDatePicker.Builder<*> = MaterialDatePicker.Builder.datePicker()
            val picker : MaterialDatePicker<*> = builder.build()
            picker.show(supportFragmentManager, picker.toString())*//*
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener
            {view, mYear, mMonth, mDayOfMonth -> tf_login.hint = ""+mYear+"/"+mMonth+"/"+mDayOfMonth }, year, month, day)
            dpd.show()
        }*/
    }
}
