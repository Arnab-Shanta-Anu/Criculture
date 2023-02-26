package com.arnab.criculture

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    companion object {
        var network = false
    }

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setSupportActionBar(findViewById(R.id.custom_appbar))

        //check network
        if (checkNetwork(this))
            network = true
        else {
            //Toast.makeText(this, "please connect to network", Toast.LENGTH_LONG).show()
            Snackbar.make(
                findViewById(R.id.fragmentContainerView),
                "please connect to network",
                Snackbar.LENGTH_SHORT
            ).show()
        }

        //getting global context
        ThisApplication(this)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)

        val bottomNav: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNav.setOnItemSelectedListener {
            val fragment =
                supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
            when (it.itemId) {
                R.id.bottom_nav_home -> {
                    if (fragment?.id != R.id.homeFragment)
                        findNavController(R.id.fragmentContainerView).navigate(R.id.homeFragment)
                }
                R.id.bottom_nav_recent -> {
                    if (fragment?.id != R.id.recentFragment)
                        findNavController(R.id.fragmentContainerView).navigate(R.id.recentFragment)
                }
                R.id.bottom_nav_schedule -> {
                    if (fragment?.id != R.id.fixtureFragment)
                        findNavController(R.id.fragmentContainerView).navigate(R.id.fixtureFragment)
                }
                R.id.bottom_nav_more -> {
                    if (fragment?.id != R.id.moreFragment)
                        findNavController(R.id.fragmentContainerView).navigate(R.id.moreFragment)
                }
            }
            true
        }
    }

    private fun checkNetwork(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp() || navController.navigateUp()
    }
}