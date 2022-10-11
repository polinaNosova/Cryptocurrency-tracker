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
import com.ua.epam.ctiptocurrencytracker.adapter.FavoriteAdapter
import com.ua.epam.ctiptocurrencytracker.databinding.FragmentFavoriteBinding
import com.ua.epam.ctiptocurrencytracker.model.CurrencyRateUiModel
import com.ua.epam.ctiptocurrencytracker.viemodel.FavoriteViewModel
import com.ua.epam.ctiptocurrencytracker.viemodel.FavoriteViewModelFactory

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val favoriteViewModel by viewModels<FavoriteViewModel> { FavoriteViewModelFactory(requireActivity().application) }

    private val adapter by lazy { FavoriteAdapter(favoriteViewModel) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        binding.rvFavorite
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpLiveData()
        setAdapter()
        favoriteViewModel.getLocalList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setAdapter() {
        binding.rvFavorite.adapter = this@FavoriteFragment.adapter
        binding.rvFavorite.layoutManager = LinearLayoutManager(context)
    }

    private fun setUpLiveData() = with(favoriteViewModel) {
        favoriteViewModel.apply {
            liveData.observe(viewLifecycleOwner) {
                adapter.setNewCurrencyModel(it as MutableList<CurrencyRateUiModel>)
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
