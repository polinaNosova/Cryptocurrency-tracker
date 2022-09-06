package com.ua.epam.ctiptocurrencytracker.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ua.epam.ctiptocurrencytracker.R
import com.ua.epam.ctiptocurrencytracker.adapter.HomeTopCurrencyAdapter.ViewHolder
import com.ua.epam.ctiptocurrencytracker.databinding.TopCurrencyItemBinding
import com.ua.epam.ctiptocurrencytracker.model.CurrencyRateUiModel

class HomeTopCurrencyAdapter(private val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    private var startRateList = listOf<CurrencyRateUiModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TopCurrencyItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(startRateList[position])
    }

    override fun getItemCount(): Int = startRateList.size

   inner class ViewHolder(private val binding: TopCurrencyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(model: CurrencyRateUiModel) = with(binding) {
            topCurrencyNameTextView.text = model.name
            topCurrencyChangeTextView.text = model.currentPrice.toString()

            topCurrencyChangeTextView.setTextColor(context.resources.getColor(R.color.black))
            Picasso.get().load(model.image).into(topCurrencyImageView)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewCurrencyModel(model: List<CurrencyRateUiModel>) {
        startRateList = model
        notifyDataSetChanged()
    }
}
