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

        //check network
        if (checkNetwork(this))
            network = true
        else {
            //Toast.makeText(this, "please connect to network", Toast.LENGTH_LONG).show()
            Snackbar.make(
                findViewById(R.id.fragmentContainerView),
                "please connect to network",
                Snackbar.LENGTH_LONG
            ).show()
        }

        //getting global context
        ThisApplication(this)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        val bottomNav: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_nav_home -> {
                    findNavController(R.id.fragmentContainerView).navigate(R.id.homeFragment)
                }
                R.id.bottom_nav_recent -> {
                    findNavController(R.id.fragmentContainerView).navigate(R.id.recentFragment)
                }
                R.id.bottom_nav_schedule -> {
                    findNavController(R.id.fragmentContainerView).navigate(R.id.fixtureFragment)
                }
                R.id.bottom_nav_more -> {
                    findNavController(R.id.fragmentContainerView).navigate(R.id.recentFragment)
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

            // Representation of the capabilities of an active network.
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                // Indicates this network uses a Wi-Fi transport,
                // or WiFi has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                // Indicates this network uses a Cellular transport. or
                // Cellular has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                // else return false
                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }
}