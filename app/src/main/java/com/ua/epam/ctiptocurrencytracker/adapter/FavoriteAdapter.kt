package com.ua.epam.ctiptocurrencytracker.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ua.epam.ctiptocurrencytracker.databinding.FavoriteCoinItemBinding
import com.ua.epam.ctiptocurrencytracker.model.CurrencyRateUiModel
import com.ua.epam.ctiptocurrencytracker.viemodel.FavoriteViewModel

class FavoriteAdapter(private val favoriteViewModel: FavoriteViewModel) :
    RecyclerView.Adapter<FavoriteAdapter.CurrencyViewHolder>() {
    var startRateList: MutableList<CurrencyRateUiModel> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CurrencyViewHolder {
        val binding = FavoriteCoinItemBinding.inflate(LayoutInflater.from(parent.context))
        return CurrencyViewHolder(binding, this)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(startRateList[position])
    }

    override fun getItemCount(): Int = startRateList.size

    fun removeItem(position: Int) {
        startRateList.removeAt(position)
        notifyItemRemoved(position)
        try {
            favoriteViewModel.deleteCoin(startRateList.get(position))
        } catch (e: IndexOutOfBoundsException) {
            e.message
        }
    }

    class CurrencyViewHolder(
        private val binding: FavoriteCoinItemBinding,
        private val adapter: FavoriteAdapter,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: CurrencyRateUiModel) = with(binding) {
            coinName.text = model.name
            coinSymbol.text = model.symbol
            coinPrice.text = String.format("%.3f", model.currentPrice)
            priceChange.text = String.format("%.3f", model.priceChangeResult)
            Picasso.get().load(model.image).into(coinIcon)
            priceChange.setTextColor(model.color)

            ivDelete.setOnClickListener {
                adapter.removeItem(adapterPosition)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewCurrencyModel(model: MutableList<CurrencyRateUiModel>) {
        startRateList = model
        notifyDataSetChanged()
    }
}