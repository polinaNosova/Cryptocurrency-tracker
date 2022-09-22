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
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.ua.epam.ctiptocurrencytracker.R
import com.ua.epam.ctiptocurrencytracker.adapter.HomeAdapter
import com.ua.epam.ctiptocurrencytracker.databinding.FragmentHomeBinding
import com.ua.epam.ctiptocurrencytracker.viemodel.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel> { HomeViewModelFactory(requireActivity().application) }
    private val adapter by lazy { context?.let { HomeAdapter(it, viewModel) } }
    private lateinit var lineEntry: ArrayList<Entry>
    private lateinit var dataset: LineDataSet

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        setUpAdapter()
        setUpLiveData()
        viewModel.getCoinsList()
        return binding.root
    }

    private fun setUpAdapter() {
        binding.rvTopCurrencyList.adapter = this@HomeFragment.adapter
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setUpLiveData() {
        viewModel.apply {
            liveData.observe(viewLifecycleOwner) {
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