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
import com.ua.epam.ctiptocurrencytracker.adapter.HomeTopCurrencyAdapter
import com.ua.epam.ctiptocurrencytracker.adapter.TopCoinsAdapter
import com.ua.epam.ctiptocurrencytracker.databinding.FragmentHomeBinding
import com.ua.epam.ctiptocurrencytracker.viemodel.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { context?.let { HomeTopCurrencyAdapter(it) } }
    private val topCoinAdapter by lazy { context?.let { TopCoinsAdapter() } }

    private val viewModel by viewModels<HomeViewModel> { HomeViewModelFactory() }
    private val topCoinViewModel by viewModels<TopCoinsViewModel> { TopCoinsViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setTopCoinsAdapter() {
        binding.rvTopCoinsList.adapter = this@HomeFragment.topCoinAdapter
        binding.rvTopCoinsList.layoutManager = LinearLayoutManager(context)
    }
    private fun setUpAdapter() {
        binding.rvTopCurrencyList.adapter = this@HomeFragment.adapter
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        setUpLiveData()
        setTopCoinsAdapter()
        setUpCoinsTopLiveData()
        topCoinViewModel.getTopCoins()
        viewModel.getCurrencyRates(MarketViewModel.QUERY)
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

    private fun setUpCoinsTopLiveData() {
        topCoinViewModel.apply {
            mapAction.observe(viewLifecycleOwner) {
                topCoinAdapter?.setNewCurrencyModel(it)
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