package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.ageinminutes.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.time.Month
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val butttonDatePicker = binding.btnDatePicker
        val tvSelectedDate=binding.tvSelectedDate
        val datePicker = binding.tvSelectedDate

        butttonDatePicker.setOnClickListener { view ->
            clickDatePicker(view)

        }

    }

    fun clickDatePicker(view: View) {
        val tvSelectedDate=binding.tvSelectedDate
        val tvSelectDateInMinutes=binding.tvSelectedInMinutes
        val tvSelectMonthInMinutes=binding.selectedMonth

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
       val dpd=  DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayofMonth ->
           Toast.makeText(
               this,
               "The chosen year is $selectedYear ,Month:$selectedMonth,Day:$selectedDayofMonth ",
               Toast.LENGTH_LONG
           ).show()
           val selectedDate = "$selectedDayofMonth/${selectedMonth + 1}/$selectedYear"
           val selectMonthHandle = selectedMonth + 1
           tvSelectedDate.text = selectedDate

           //Calculate Minutes
           val sdf = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR"))

           val theDate = sdf.parse(selectedDate)

           val selectDateInMinutes = theDate!!.time / 6000
           val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

           val currentDateToMinutes = currentDate!!.time / 6000
           val differenceInMinutes = currentDateToMinutes - selectDateInMinutes

           tvSelectDateInMinutes.text = differenceInMinutes.toString()
           //Calculate Month
           val currentYear = Calendar.getInstance().get(Calendar.YEAR)
           val age = currentYear - selectedYear
           val monthAge = age * 12
           val monthYear = monthAge - selectMonthHandle

           tvSelectMonthInMinutes.text = monthYear.toString()




        }, year, month, day)
        dpd.datePicker.maxDate = Date().time -86400000
        dpd.show()
    }
}


