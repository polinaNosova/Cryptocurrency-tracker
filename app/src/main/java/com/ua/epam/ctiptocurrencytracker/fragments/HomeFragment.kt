package com.ua.epam.ctiptocurrencytracker.fragments

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
import com.ua.epam.ctiptocurrencytracker.adapter.ViewPagerAdapter
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
        setUpLiveData()
        viewModel.getCoinsList()
        initiate()
        setTabLayout()
        return binding.root
    }

    private fun setTabLayout() {
        binding.contentViewPager.registerOnPageChangeCallback(object :
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
    }

    private fun initiate() {
        binding.contentViewPager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.contentViewPager) { tab, pos ->
            var title = if (pos == 0) {
                "Top Gainers"
            } else {
                "Top Losers"
            }
            tab.text = title
        }.attach()
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