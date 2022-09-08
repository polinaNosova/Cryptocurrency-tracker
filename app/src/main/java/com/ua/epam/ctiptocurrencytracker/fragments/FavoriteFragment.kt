package com.ua.epam.ctiptocurrencytracker.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.ua.epam.ctiptocurrencytracker.utils.SingleLiveEvent
import com.ua.epam.ctiptocurrencytracker.R
import com.ua.epam.ctiptocurrencytracker.adapter.MarketAdapter
import com.ua.epam.ctiptocurrencytracker.databinding.FragmentFavoriteBinding
import com.ua.epam.ctiptocurrencytracker.model.CurrencyRateUiModel
import com.ua.epam.ctiptocurrencytracker.viemodel.FavoriteViewModel
import com.ua.epam.ctiptocurrencytracker.viemodel.FavoriteViewModelFactory

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val _mapAction = MutableLiveData<List<CurrencyRateUiModel>>()
    val mapAction: LiveData<List<CurrencyRateUiModel>> get() = _mapAction

    private val adapter by lazy { MarketAdapter() }
    private val _errorAction = SingleLiveEvent<String>()
    val errorAction: LiveData<String> get() = _errorAction

    private val favoriteViewModel by viewModels<FavoriteViewModel> {
        FavoriteViewModelFactory(
            requireActivity().application
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        setUpLiveData()
        favoriteViewModel.getCurrencyRates()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpAdapter() {
        binding.apply {
            rvFavorite.adapter = adapter
            rvFavorite.layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setUpLiveData() = with(favoriteViewModel) {
        favoriteViewModel.apply {
            mapAction.observe(viewLifecycleOwner) {
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
