package com.example.ksu_project_mobile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.ksu_project_mobile.R
import com.example.ksu_project_mobile.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {
    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)

        // Toolbar setup
        val toolbar: Toolbar = binding.toolbar
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        val toolbarTitle: TextView = binding.root.findViewById(R.id.toolbar_title)
        toolbarTitle.text = "Уведомления"

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
