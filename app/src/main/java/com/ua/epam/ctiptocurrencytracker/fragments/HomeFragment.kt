package com.ua.epam.ctiptocurrencytracker.fragments

import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.ua.epam.ctiptocurrencytracker.R
import com.ua.epam.ctiptocurrencytracker.adapter.HomeAdapter
import com.ua.epam.ctiptocurrencytracker.adapter.TopGainPagerAdapter
import com.ua.epam.ctiptocurrencytracker.databinding.FragmentHomeBinding
import com.ua.epam.ctiptocurrencytracker.viemodel.*


class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel> { HomeViewModelFactory(requireActivity().application) }

    private val adapter by lazy { context?.let { HomeAdapter() } }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        setUpAdapter()
        setUpLiveData()
        viewModel.getCoinsList()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTabLayout()
    }

    private fun setTabLayout() {
        val adapter = TopGainPagerAdapter(this)
        binding.pager.adapter = adapter

        binding.pager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 0) {
                    binding.topGainIndicator.visibility = VISIBLE
                    binding.topLoseIndicator.visibility = GONE
                } else {
                    binding.topGainIndicator.visibility = GONE
                    binding.topLoseIndicator.visibility = VISIBLE
                }
            }
        })
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            val title = if (position == 0) {
                "Top Gainers"
            } else {
                "Top Losers"
            }
            tab.text = title
        }.attach()
    }

    private fun setUpAdapter() {
        binding.rvTopCurrencyList.adapter = this@HomeFragment.adapter
    }

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