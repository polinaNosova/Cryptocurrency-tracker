package com.ua.epam.ctiptocurrencytracker.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.*
import com.squareup.picasso.Picasso
import com.ua.epam.ctiptocurrencytracker.R
import com.ua.epam.ctiptocurrencytracker.adapter.MarketAdapter
import com.ua.epam.ctiptocurrencytracker.databinding.FragmentDetailBinding
import com.ua.epam.ctiptocurrencytracker.model.CurrencyRateUiModel
import com.ua.epam.ctiptocurrencytracker.viemodel.MarketViewModel
import com.ua.epam.ctiptocurrencytracker.viemodel.MarketViewModelFactory

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy { MarketAdapter() }
    private val viewModel by viewModels<MarketViewModel> { MarketViewModelFactory() }

    private val args: DetailFragmentArgs by navArgs()
    private lateinit var lineEntry: ArrayList<Entry>
    private lateinit var dataset: LineDataSet

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val data: CurrencyRateUiModel = args.data!!
        setUpDetails(data)
        binding.lineChart.data = setUpChart(data)
        binding.lineChart.setBackgroundColor(resources.getColor(R.color.purple_200))
        binding.lineChart.animateX(3000)
        return binding.root
    }

    private fun setUpDetails(data: CurrencyRateUiModel) {
        binding.detailSymbolTextView.text = data.name
        Picasso.get().load(data.image).into(binding.detailImageView)
        binding.detailPriceTextView.text = String.format("%.3f", data.currentPrice)
        binding.detailChangeTextView.text = String.format("%.3f", data.priceChangeResult)
        binding.detailChangeTextView.setTextColor(data.color)
        binding.tvMarkupRank.text = data.market_cap_rank.toString()
    }

    private fun setUpChart(data: CurrencyRateUiModel): LineData {
        lineEntry = ArrayList()
        lineEntry.add(Entry(1f, data.sparklineIn7dEntity.price[0]))
        lineEntry.add(BarEntry(24f, data.sparklineIn7dEntity.price[24]))
        lineEntry.add(BarEntry(48f, data.sparklineIn7dEntity.price[48]))
        lineEntry.add(BarEntry(72f, data.sparklineIn7dEntity.price[72]))
        lineEntry.add(BarEntry(96f, data.sparklineIn7dEntity.price[96]))
        lineEntry.add(BarEntry(120f, data.sparklineIn7dEntity.price[120]))

        dataset = LineDataSet(lineEntry, "value")
        return LineData(dataset)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
