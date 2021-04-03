package com.denis.foxpictures

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_picture.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv_foxes.layoutManager = LinearLayoutManager(this)
        rv_foxes.adapter = FoxesAdapter(fillList())




    }
    private fun fillList(): List<String> {
        val data = mutableListOf<String>()
        (0..15).forEach { i -> data.add("${i+1} изображение") }
        return data
    }
}