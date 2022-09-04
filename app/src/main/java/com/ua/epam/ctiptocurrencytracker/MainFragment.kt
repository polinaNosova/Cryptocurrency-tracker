package com.ua.epam.ctiptocurrencytracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ua.epam.ctiptocurrencytracker.databinding.FragmentAuthorisationBinding
import com.ua.epam.ctiptocurrencytracker.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomNavMenu = view.findViewById<BottomNavigationView>(R.id.bottom_navigation_menu)
        val navController =
            (childFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment).navController
        NavigationUI.setupWithNavController(bottomNavMenu, navController)
    }
}