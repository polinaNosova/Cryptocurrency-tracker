package com.ua.epam.ctiptocurrencytracker.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ua.epam.ctiptocurrencytracker.R
import com.ua.epam.ctiptocurrencytracker.databinding.CoinItemBinding
import com.ua.epam.ctiptocurrencytracker.fragments.MarketFragmentDirections
import com.ua.epam.ctiptocurrencytracker.model.CurrencyRateUiModel

class FavoriteAdapter : RecyclerView.Adapter<MarketAdapter.CurrencyViewHolder>() {
    private var startRateList = listOf<CurrencyRateUiModel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarketAdapter.CurrencyViewHolder {
        val binding = CoinItemBinding.inflate(LayoutInflater.from(parent.context))
        return MarketAdapter.CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MarketAdapter.CurrencyViewHolder, position: Int) {
        holder.bind(startRateList[position])
    }

    override fun getItemCount(): Int = startRateList.size

    class CurrencyViewHolder(private val binding: CoinItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: CurrencyRateUiModel) = with(binding) {
            coinName.text = model.name
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