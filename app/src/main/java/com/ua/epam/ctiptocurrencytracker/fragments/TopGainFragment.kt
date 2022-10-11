package com.ua.epam.ctiptocurrencytracker.fragments

import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ua.epam.ctiptocurrencytracker.adapter.MarketAdapter
import com.ua.epam.ctiptocurrencytracker.databinding.TopGainFragmentBinding
import com.ua.epam.ctiptocurrencytracker.model.CurrencyRateUiModel
import com.ua.epam.ctiptocurrencytracker.viemodel.TopGainViewModel
import com.ua.epam.ctiptocurrencytracker.viemodel.TopGainViewModelFactory

class TopGainFragment : Fragment() {
    private var _binding: TopGainFragmentBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy { MarketAdapter() }
    private val viewModel by viewModels<TopGainViewModel> { TopGainViewModelFactory(requireActivity().application) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = TopGainFragmentBinding.inflate(inflater, container, false)
        setUpAdapter()
        setUpLiveData()
        viewModel.getCoinsList()
        return binding.root
    }
    private fun setUpAdapter() {
        binding.apply {
            topGainLoseRecyclerView.adapter = this@TopGainFragment.adapter
            topGainLoseRecyclerView.layoutManager = LinearLayoutManager(context)
        }
    }

    private fun getTopCoins(){
       val list = viewModel.getCoinsList() 
    }

    private fun setUpLiveData() {
        viewModel.apply {
            liveData.observe(viewLifecycleOwner) {
                adapter.setNewCurrencyModel(it)
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