package com.example.safetyfirst

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomBar = findViewById<BottomNavigationView>(R.id.nav_bar)
        bottomBar.setOnItemReselectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_security -> {
                    inflatefragment(GuardFragment.newInstance())
                }
                R.id.nav_home -> {
                    inflatefragment(homeFragment.newInstance())
                }
                R.id.nav_dashboard -> {
                    inflatefragment(MapsFragment())
                }
                R.id.nav_profile -> {
                    inflatefragment(ProfileFragment.newInstance())
                }
            }
            true
        }
        bottomBar.selectedItemId = R.id.nav_home
    }
    private fun inflatefragment(newInstance: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, newInstance)
        transaction.commit()
    }
}