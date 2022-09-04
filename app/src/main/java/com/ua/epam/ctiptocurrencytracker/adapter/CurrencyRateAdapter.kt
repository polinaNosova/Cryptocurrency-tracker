package com.ua.epam.ctiptocurrencytracker.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

import com.ua.epam.ctiptocurrencytracker.databinding.CoinItemBinding
import com.ua.epam.ctiptocurrencytracker.model.CurrencyRateUiModel

class CurrencyRateAdapter() :
    RecyclerView.Adapter<CurrencyRateAdapter.CurrencyViewHolder>() {
    private var startRateList = listOf<CurrencyRateUiModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val binding = CoinItemBinding.inflate(LayoutInflater.from(parent.context))
        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(startRateList[position])
    }

    override fun getItemCount(): Int = startRateList.size

    class CurrencyViewHolder(private val binding: CoinItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: CurrencyRateUiModel) = with(binding) {
            coinName.text = model.name
            coinSymbol.text = model.symbol
            coinPrice.text = model.currentPrice.toString()
            priceChange.text = model.priceChangeResult.toString()
            Picasso.get().load(model.image).into(coinIcon)
            priceChange.setTextColor(model.color)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewCurrencyModel(model: List<CurrencyRateUiModel>) {
        startRateList = model
        notifyDataSetChanged()
    }
}