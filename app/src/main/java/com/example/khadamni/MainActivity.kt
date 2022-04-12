package com.example.khadamni

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val drawerLayout: DrawerLayout=findViewById(R.id.drawerLayout);
        val drawerNavView: NavigationView = findViewById(R.id.nav_view)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setSupportActionBar(findViewById(R.id.toolBar));
        toggle =  ActionBarDrawerToggle(this,drawerLayout,findViewById(R.id.toolBar),R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        drawerNavView.setNavigationItemSelectedListener {
            when (it.itemId)
            {
                R.id.search -> {
                    Toast.makeText(applicationContext, "clicked on search",Toast.LENGTH_SHORT).show()
                    Intent(this,Register::class.java).also {
                        startActivity(it)
                    }
                }
                R.id.profile -> {
                    Toast.makeText(applicationContext, "clicked on profile",Toast.LENGTH_SHORT).show()
                    Intent(this,UserProfile::class.java).also {
                        startActivity(it)
                    }

                }

            }
            true
        }

    }
}