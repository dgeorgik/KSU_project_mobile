package com.example.ksu_project_mobile.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.ksu_project_mobile.R
import com.example.ksu_project_mobile.databinding.FragmentHomeBinding
import com.example.ksu_project_mobile.models.UserViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class HomeFragment : Fragment() {
    private val userViewModel: UserViewModel by activityViewModels()
    private var _binding: FragmentHomeBinding? = null

    private val binding: FragmentHomeBinding
        get() = _binding ?: throw IllegalStateException("View binding is only available [HomeFragment]")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navView: NavigationView = view.findViewById(R.id.nav_view)
        val headerView: View = navView.getHeaderView(0)
        val tvUserName: TextView = headerView.findViewById(R.id.tv_user_name)

         userViewModel.userName.observe(viewLifecycleOwner) { userName ->
            tvUserName.text = userName
        }

         userViewModel.userRole.observe(viewLifecycleOwner) { userRole ->
            val toolbarTitle: TextView = view.findViewById(R.id.toolbar_title)
            toolbarTitle.text = "Главная [$userRole]"
        }

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_employees -> {
                    findNavController().navigate(R.id.action_to_employeeFragment)
                    true
                }
                R.id.nav_profile -> {
                    findNavController().navigate(R.id.myProfileFragment)
                    true
                }
                R.id.nav_reports  -> {
                    findNavController().navigate(R.id.companyReportsFragment)
                    true
                }
                R.id.nav_current_reports  -> {
                    findNavController().navigate(R.id.currentContracts)
                    true
                }
                R.id.nav_calendar  -> {
                    findNavController().navigate(R.id.calendarFragment)
                    true
                }
                else -> false
            }.also {
                val drawerLayout = view.findViewById<DrawerLayout>(R.id.drawer_layout)
                drawerLayout.closeDrawer(GravityCompat.START)
            }
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_notifications -> {
                findNavController().navigate(R.id.notificationsFragment)
                true
            }
                R.id.navigation_accounting -> {
                findNavController().navigate(R.id.accountingFragment)
                true
            }
                R.id.navigation_assets -> {
                    findNavController().navigate(R.id.assetsFragment)
                    true
                }
                R.id.navigation_accounts -> {
                    findNavController().navigate(R.id.accountsFragment)
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

        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayShowTitleEnabled(false)

        return view
    }
}