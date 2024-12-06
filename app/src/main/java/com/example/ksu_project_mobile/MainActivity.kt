package com.example.ksu_project_mobile

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.ksu_project_mobile.fragments.AppDatabase
import com.example.ksu_project_mobile.fragments.UserDao
import com.example.ksu_project_mobile.fragments.UserRepository
import com.example.ksu_project_mobile.fragments.UserViewModelFactory
import com.example.ksu_project_mobile.models.UserViewModel
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var userViewModel: UserViewModel
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val navView: NavigationView = findViewById(R.id.nav_view)
        val headerView: View = navView.getHeaderView(0)
        val tvUserName: TextView = headerView.findViewById(R.id.tv_user_name)

        val database = AppDatabase.getInstance(applicationContext)
        val userDao = database.userDao()
        val repository = UserRepository(userDao)
        val factory = UserViewModelFactory(repository)
        userViewModel = ViewModelProvider(this, factory)[UserViewModel::class.java]

        userViewModel.initData()

         userViewModel.currentUser.observe(this, Observer { user ->
            user?.let {
                tvUserName.text = it.name
            }
        })

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController

         setupActionBarWithNavController(navController)

         navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_profile -> {
                     Log.i("MainActivity", "Profile clicked")
                    true
                }
                R.id.nav_reports -> {
                     Log.i("MainActivity", "Reports clicked")
                    true
                }
                R.id.nav_employees -> {
                     Log.i("MainActivity", "Employees clicked")
                    navController.navigate(R.id.action_to_employeeFragment)
                    true
                }
                else -> false
            }.also {
                drawerLayout.closeDrawer(GravityCompat.START)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
