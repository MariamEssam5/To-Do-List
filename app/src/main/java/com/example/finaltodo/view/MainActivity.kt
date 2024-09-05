package com.example.finaltodo.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentTransaction
import com.example.finaltodo.R

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.drawer_open, R.string.drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null) {
            // Set default fragment
            replaceFragment(HomeFragment())
            navView.setCheckedItem(R.id.nav_home)
        }

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    replaceFragment(HomeFragment())
                }
                R.id.personal -> {
                    replaceFragment(PersonalTodosFragment())
                }
                R.id.home -> {
                    replaceFragment(HomeTodosFragment())
                }
                R.id.work -> {
                    replaceFragment(WorkTodosFragment())
                }
                R.id.complete -> {
                    replaceFragment(CompletedTasksFragment())
                }
                R.id.nav_settings -> {
                    replaceFragment(SettingsFragment())
                }
                R.id.nav_share_facebook -> {
                }
                R.id.nav_share_twitter -> {
                }
            }
            drawerLayout.closeDrawers()
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null) // Add to back stack
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE) // Optional transition
            .commit()
    }
}
