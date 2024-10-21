package com.example.ksu_project_mobile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.ksu_project_mobile.R
import com.example.ksu_project_mobile.models.UserViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class HomeFragment : Fragment() {
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navView: NavigationView = view.findViewById(R.id.nav_view)

        val headerView: View = navView.getHeaderView(0)

        val tvUserName: TextView = headerView.findViewById(R.id.tv_user_name)

        userViewModel.userName.observe(viewLifecycleOwner) { userName ->
            tvUserName.text = userName
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_notifications -> {
                    true
                }
                R.id.navigation_accounting -> {
                    true
                }
                R.id.navigation_assets -> {
                    true
                }
                R.id.navigation_accounts -> {
                    true
                }
                R.id.navigation_menu -> {
                    val drawerLayout = view.findViewById<DrawerLayout>(R.id.drawer_layout)
                    drawerLayout.openDrawer(GravityCompat.START)
                    true
                }
                else -> false
            }
        }

        return view
    }
}