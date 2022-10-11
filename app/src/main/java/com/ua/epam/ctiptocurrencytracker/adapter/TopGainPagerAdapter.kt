package com.ua.epam.ctiptocurrencytracker.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TopGainPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = TopGainFragment()
        val bundle = Bundle()
        bundle.putInt("position",position)
        fragment.
    }
}