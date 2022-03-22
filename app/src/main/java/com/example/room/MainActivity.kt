package com.example.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val navHostFragment= supportFragmentManager.primaryNavigationFragment

    }

    override fun onSupportNavigateUp(): Boolean {
        setupActionBarWithNavController(findNavController(R.id.fragment))
        val navHostFragment =  supportFragmentManager
            .findFragmentById(R.id.fragment) as NavHostFragment
        val navController=navHostFragment.navController
        return navController.navigateUp()||super.onSupportNavigateUp()
    }
}