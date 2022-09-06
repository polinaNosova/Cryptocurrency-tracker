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
import com.ua.epam.ctiptocurrencytracker.R
import com.ua.epam.ctiptocurrencytracker.adapter.HomeTopCurrencyAdapter
import com.ua.epam.ctiptocurrencytracker.databinding.FragmentHomeBinding
import com.ua.epam.ctiptocurrencytracker.viemodel.HomeViewModel
import com.ua.epam.ctiptocurrencytracker.viemodel.HomeViewModelFactory
import com.ua.epam.ctiptocurrencytracker.viemodel.MarketViewModel

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

//    private fun setTabLayout() {
//        val adapter = LossGainPagerAdapter(this)
//        binding.contentViewPager.adapter = adapter
//        binding.contentViewPager.registerOnPageChangeCallback(object :
//            ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//                if (position == 0) {
//                    binding.topGainIndicator.visibility = VISIBLE
//                    binding.topLoseIndicator.visibility = GONE
//                }else{
//                    binding.topGainIndicator.visibility = GONE
//                    binding.topLoseIndicator.visibility = VISIBLE
//                }
//            }
//        })
//        TabLayoutMediator(binding.tabLayout,binding.contentViewPager){
//            tab,position ->
//            var title = if(position == 0){
//                "Top Gainers"
//            }else{
//                "Top Losers"
//            }
//            tab.text = title
//        }.attach()
//    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        setUpLiveData()
        viewModel.getCurrencyRates(MarketViewModel.QUERY)
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