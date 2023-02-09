package com.arnab.criculture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arnab.criculture.adapters.RecyclerViewAdapter
import com.arnab.criculture.viewmodel.CricultureViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val viewModel = ViewModelProvider(this)[CricultureViewModel::class.java]

        viewModel.allTeams.observe(this){
            recyclerView.adapter = RecyclerViewAdapter(this,it.data)
            recyclerView.layoutManager = LinearLayoutManager(this)
        }

    }
}