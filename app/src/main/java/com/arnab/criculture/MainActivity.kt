package com.arnab.criculture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arnab.criculture.adapters.RecyclerViewAdapter
import com.arnab.criculture.viewmodel.CricultureViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment=supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController=navHostFragment.navController
        val bottomNav:BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.bottom_nav_home -> {findNavController(R.id.fragmentContainerView).navigate(R.id.homeFragment)}
                R.id.bottom_nav_recent -> {findNavController(R.id.fragmentContainerView).navigate(R.id.recentFragment)}
                R.id.bottom_nav_schedule -> {findNavController(R.id.fragmentContainerView).navigate(R.id.fixtureFragment)}
                R.id.bottom_nav_more -> {findNavController(R.id.fragmentContainerView).navigate(R.id.recentFragment)}
            }
            true
        }
    }
}