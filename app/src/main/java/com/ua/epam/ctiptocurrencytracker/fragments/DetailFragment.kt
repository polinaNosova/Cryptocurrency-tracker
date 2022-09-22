package com.ua.epam.ctiptocurrencytracker.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.github.mikephil.charting.data.*
import com.squareup.picasso.Picasso
import com.ua.epam.ctiptocurrencytracker.R
import com.ua.epam.ctiptocurrencytracker.databinding.FragmentDetailBinding
import com.ua.epam.ctiptocurrencytracker.model.CurrencyRateUiModel
import com.ua.epam.ctiptocurrencytracker.model.CurrencyUiMapper
import com.ua.epam.ctiptocurrencytracker.viemodel.DetailViewModel
import com.ua.epam.ctiptocurrencytracker.viemodel.DetailViewModelFactory
import com.ua.epam.ctiptocurrencytracker.viemodel.FavoriteViewModelFactory

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<DetailViewModel> {
        DetailViewModelFactory(
            requireActivity().application
        )
    }
    private val args: DetailFragmentArgs by navArgs()

    private lateinit var lineEntry: ArrayList<Entry>
    private lateinit var dataset: LineDataSet
    private val flag: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val data: CurrencyRateUiModel = args.data
        setUpDetails(data)
        binding.lineChart.data = setUpChart(data)
        binding.lineChart.setBackgroundColor(resources.getColor(R.color.purple_200))
        binding.lineChart.animateX(3000)

        binding.backStackButton.setOnClickListener {
            findNavController()
                .navigate(R.id.action_detailFragment_to_marketFragment2)
        }
        binding.saveStarBtn.setOnClickListener {
            if (flag) {
                binding.saveStarBtn.setImageResource(R.drawable.star_black)
            } else {
                binding.saveStarBtn.setImageResource(R.drawable.ic_baseline_star_outline_24)
            }
            saveData(data)
            newInstance(data.id)
        }
        return binding.root
    }

    private fun saveData(data: CurrencyRateUiModel) {
        viewModel.addCoinToLocalStorage(data)
    }

    private fun setUpDetails(data: CurrencyRateUiModel) {
        binding.detailSymbolTextView.text = data.name
        Picasso.get().load(data.image).into(binding.detailImageView)
        binding.detailPriceTextView.text = String.format("%.3f", data.currentPrice)
        binding.detailChangeTextView.text = String.format("%.3f", data.priceChangeResult)
        binding.detailChangeTextView.setTextColor(data.color)
    }

    private fun setUpChart(data: CurrencyRateUiModel): LineData {
        lineEntry = ArrayList()
        lineEntry.add(Entry(1f, data.sparkline_in_7d[0]))
        lineEntry.add(BarEntry(24f, data.sparkline_in_7d[24]))
        lineEntry.add(BarEntry(48f, data.sparkline_in_7d[48]))
        lineEntry.add(BarEntry(72f, data.sparkline_in_7d[72]))
        lineEntry.add(BarEntry(96f, data.sparkline_in_7d[96]))
        lineEntry.add(BarEntry(120f, data.sparkline_in_7d[120]))

        dataset = LineDataSet(lineEntry, "value")
        return LineData(dataset)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(id: String): DetailFragment {
            val bundle = Bundle()
            bundle.putString("ID_KEY", id)
            val fragment = DetailFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
