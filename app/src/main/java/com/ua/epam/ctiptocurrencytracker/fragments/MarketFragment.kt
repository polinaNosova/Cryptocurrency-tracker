package com.ua.epam.ctiptocurrencytracker.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ua.epam.ctiptocurrencytracker.R
import com.ua.epam.ctiptocurrencytracker.adapter.MarketAdapter
import com.ua.epam.ctiptocurrencytracker.databinding.FragmentMarketBinding
import com.ua.epam.ctiptocurrencytracker.model.CurrencyRateUiModel
import com.ua.epam.ctiptocurrencytracker.viemodel.MarketViewModel
import com.ua.epam.ctiptocurrencytracker.viemodel.MarketViewModelFactory

class MarketFragment : Fragment(R.layout.fragment_favorite) {

    private var _binding: FragmentMarketBinding? = null
    private val binding get() = _binding!!
    private val adapter by lazy { MarketAdapter() }
    private val viewModel by viewModels<MarketViewModel> { MarketViewModelFactory(requireActivity().application) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentMarketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        setUpLiveData()
        viewModel.getCoinsList()
        addCoinToLocalStorage(adapter.startList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpAdapter() {
        binding.apply {
            rvCoins.adapter = this@MarketFragment.adapter
            rvCoins.layoutManager = LinearLayoutManager(context)
        }
    }

    private fun addCoinToLocalStorage(currencyList: List<CurrencyRateUiModel>) {
        for (i in currencyList) {
            viewModel.addCoinToLocalStorage(i)
        }
    }

    private fun setUpLiveData() {
        viewModel.apply {
            liveData.observe(viewLifecycleOwner) {
                adapter.setNewCurrencyModel(it)
                errorAction.observe(viewLifecycleOwner) {
                    Toast.makeText(
                        context,
                        "No Internet Connection",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    companion object {
        const val userNameKey = "USER_NAME"
        const val userEmailKey = "USER_EMAIL"
        const val userIdKey = "USER_ID"

        fun newInstance(id: String) =
            MarketFragment().apply {
                arguments = Bundle().apply {
                    putString(userIdKey, id)
                }
            }
    }
}