package com.example.datespinnertest

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import java.util.Calendar.*


//https://qiita.com/DreamHanks/items/bc3d0f463954395a6e12
class MainActivity : AppCompatActivity() {

    var weekday: String = ""
    var dayOfMonth: String = ""


    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val spinnerMonth: Spinner = findViewById(R.id.spinnerMonth)
        val spinnerYear: Spinner = findViewById(R.id.spinnerYear)
        val spinnerDateDay: Spinner = findViewById(R.id.spinnerDateDay)

        //日(曜日)のArrayList
        val list: ArrayList<String?> = ArrayList()

        //xmlファイルからアイテムの配列を取得
        val items = resources.getStringArray(R.array.month_array)
        val items2 = resources.getStringArray(R.array.year_array)
        //val items3 = resources.getStringArray(R.array.date_array)

        //アダプターにアイテム配列を設定
        val Adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        val Adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items2)
        //val Adapter3 = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items3)
        val dateSpn = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list as ArrayList<*>)

        //スピナーにアダプターを設定
        val today = getInstance()
        var yearPosition: Int = 0

        spinnerMonth.adapter = Adapter
        spinnerYear.adapter = Adapter2
        //spinnerDateDay.adapter = Adapter3
        spinnerDateDay.adapter = dateSpn

        spinnerYear.setSelection(today.get(YEAR))
        if(today.get(YEAR) == 2021) {
            yearPosition = 1
            spinnerYear.setSelection(yearPosition)
        }

        spinnerMonth.setSelection(today.get(MONTH))
        //Log.v("MainActivity", "test10")


        dayOfMonth = today.get(DAY_OF_MONTH).toString()
        weekday = today.get(DAY_OF_WEEK).toString()
        when (today.get(DAY_OF_WEEK)) {
            1 -> weekday = "日"
            2 -> weekday = "月"
            3 -> weekday = "火"
            4 -> weekday = "水"
            5 -> weekday = "木"
            6 -> weekday = "金"
            7 -> weekday = "土"
        }



      today.add(Calendar.DAY_OF_MONTH, 1).toString()
      today.add(Calendar.DAY_OF_WEEK, 1).toString()


        //spinnerDateDay.setSelection(today.get(Calendar.DAY_OF_MONTH))
        list.add(dayOfMonth + "日 (" + weekday + ")")
        list.add(dayOfMonth + "日 (" + weekday + ")")



        //スピナーのセレクトイベント設定
        spinnerDateDay.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val spinnerParent = parent as Spinner
                spinnerParent.selectedItem as String
                spinnerDateDay.getItemAtPosition(position)


            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }


    }
}



