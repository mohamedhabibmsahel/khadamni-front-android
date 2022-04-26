package com.example.khadamni

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.khadamni.Controller.Login
import com.example.khadamni.Controller.jobs.JobsFragment
import com.example.khadamni.Controller.messages.MessagesFragment
import com.example.khadamni.Controller.services.ServicesFragment
import com.example.khadamni.Controller.worker.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle

    lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        //aaaaa

        val homeFrag = HomeFragment()
        val jobsFrag = JobsFragment()
        val profilFrag = ProfilFragment()
        val messageFrag = MessagesFragment()
        val serviceFrag = ServicesFragment()



        setCurrentFragment(homeFrag)

        bottomNav = findViewById(R.id.bottomNavigationView)

        bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.mihome -> setCurrentFragment(homeFrag)
                R.id.mimessage -> setCurrentFragment(messageFrag)
                R.id.miprofil -> setCurrentFragment(profilFrag)
                R.id.miservice -> setCurrentFragment(serviceFrag)
                R.id.mijob -> setCurrentFragment(jobsFrag)
            }
            true
        }

        bottomNav.getOrCreateBadge(R.id.mimessage).apply {
            number = 8
            isVisible = true
        }


        //aaaaa

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
                R.id.logout -> {
                    val dialogBuilder = AlertDialog.Builder(this)
                    // set message of alert dialog
                    dialogBuilder.setMessage("Are you sur you want to logout ?")
                        // if the dialog is cancelables
                        .setCancelable(false)
                        // positive button text and action
                        .setPositiveButton("Yes", DialogInterface.OnClickListener {
                                dialog, id ->
                            getSharedPreferences("SHARED_PREF", MODE_PRIVATE).edit().clear().apply()
                            Intent(this, Login::class.java).also {
                                startActivity(it)
                            }                        })
                        // negative button text and action
                        .setNegativeButton("No", DialogInterface.OnClickListener {
                                dialog, id -> dialog.cancel()
                        })

                    // create dialog box
                    val alert = dialogBuilder.create()
                    // set title for alert dialog box
                    alert.setTitle("Logout")
                    // show alert dialog
                    alert.show()
                    Toast.makeText(applicationContext, "clicked on logout",Toast.LENGTH_SHORT).show()


                }

            }
            true
        }

    }
    private  fun setCurrentFragment(fragment : Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }
}