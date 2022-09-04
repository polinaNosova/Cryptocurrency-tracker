package com.ua.epam.ctiptocurrencytracker.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ua.epam.ctiptocurrencytracker.R
import com.ua.epam.ctiptocurrencytracker.adapter.CurrencyRateAdapter
import com.ua.epam.ctiptocurrencytracker.adapter.HomeTopCurrencyAdapter
import com.ua.epam.ctiptocurrencytracker.databinding.FragmentHomeBinding
import com.ua.epam.ctiptocurrencytracker.databinding.FragmentMarketBinding
import com.ua.epam.ctiptocurrencytracker.viemodel.HomeViewModel
import com.ua.epam.ctiptocurrencytracker.viemodel.HomeViewModelFactory
import com.ua.epam.ctiptocurrencytracker.viemodel.MainViewModel
import com.ua.epam.ctiptocurrencytracker.viemodel.MainViewModelFactory

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { context?.let { HomeTopCurrencyAdapter(it) } }
    private val viewModel by viewModels<HomeViewModel> { HomeViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        setUpLiveData()
        viewModel.getCurrencyRates(MainViewModel.QUERY)
    }

    private fun setUpAdapter() {
        binding.apply {
            rvTopCurrencyList.adapter = this@HomeFragment.adapter
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setUpLiveData() {
        viewModel.apply {
            mapAction.observe(viewLifecycleOwner) {
                adapter?.setNewCurrencyModel(it)
                errorAction.observe(viewLifecycleOwner) {
                    Toast.makeText(
                        context,
                        "An error occurred",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}