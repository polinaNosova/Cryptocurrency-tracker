package com.ua.epam.ctiptocurrencytracker.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ua.epam.ctiptocurrencytracker.databinding.CoinItemBinding
import com.ua.epam.ctiptocurrencytracker.databinding.TopCoinItemBinding
import com.ua.epam.ctiptocurrencytracker.model.CurrencyRateUiModel
import com.ua.epam.ctiptocurrencytracker.model.CurrencyUiMapper
import com.ua.epam.ctiptocurrencytracker.model.TopCoinUiModel

class TopCoinsAdapter() : RecyclerView.Adapter<TopCoinsAdapter.ViewHolder>() {
    private var startRateList = listOf<CurrencyRateUiModel>()

    class ViewHolder(private val binding: CoinItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: CurrencyRateUiModel) = with(binding) {
            coinName.text = model.name
            coinSymbol.text = model.symbol
            coinPrice.text = model.currentPrice.toString()
            priceChange.text = model.priceChangeResult.toString()
            coinPrice.text = String.format("%.3f",model.priceChangeResult)
            Picasso.get().load(model.image).into(coinIcon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CoinItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(startRateList[position])
    }

    override fun getItemCount(): Int = startRateList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setNewCurrencyModel(model: List<CurrencyRateUiModel>) {
        startRateList = model
        notifyDataSetChanged()
    }
}