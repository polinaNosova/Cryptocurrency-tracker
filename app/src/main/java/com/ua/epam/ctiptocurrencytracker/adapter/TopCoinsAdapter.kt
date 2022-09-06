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
import com.ua.epam.ctiptocurrencytracker.model.TopCoinUiModel

class TopCoinsAdapter() : RecyclerView.Adapter<TopCoinsAdapter.ViewHolder>() {
    private var startRateList = listOf<TopCoinUiModel>()

    class ViewHolder(private val binding: TopCoinItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: TopCoinUiModel) = with(binding) {
            coinName.text = model.item.name
            coinSymbol.text = model.item.symbol
            coinPrice.text = String.format("%.3f", model.item.price_btc)
            Picasso.get().load(model.item.small).into(coinIcon)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TopCoinItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        holder.bind(startRateList[position])

    }

    override fun getItemCount(): Int = startRateList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setNewCurrencyModel(model: List<TopCoinUiModel>) {
        startRateList = model
        notifyDataSetChanged()
    }
}