package com.ua.epam.ctiptocurrencytracker.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

import com.ua.epam.ctiptocurrencytracker.databinding.CoinItemBinding
import com.ua.epam.ctiptocurrencytracker.fragments.MarketFragmentDirections
import com.ua.epam.ctiptocurrencytracker.model.CurrencyRateUiModel

class MarketAdapter() :
    RecyclerView.Adapter<MarketAdapter.CurrencyViewHolder>() {

    private var startRateList = listOf<CurrencyRateUiModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val binding = CoinItemBinding.inflate(LayoutInflater.from(parent.context))
        return CurrencyViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(startRateList[position])

    }

    override fun getItemCount(): Int = startRateList.size

    class CurrencyViewHolder(private val binding: CoinItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: CurrencyRateUiModel) = with(binding) {
            coinName.text = model.name
            coinSymbol.text = model.symbol
            coinPrice.text = String.format("%.3f", model.currentPrice)
            priceChange.text = String.format("%.3f", model.priceChangeResult)
            Picasso.get().load(model.image).into(coinIcon)
            priceChange.setTextColor(model.color)
            itemView.setOnClickListener {
                findNavController(it).navigate(
                    MarketFragmentDirections.actionMarketFragment2ToDetailFragment(model)
                )
            }

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewCurrencyModel(model: List<CurrencyRateUiModel>) {
        startRateList = model
        notifyDataSetChanged()
    }
}