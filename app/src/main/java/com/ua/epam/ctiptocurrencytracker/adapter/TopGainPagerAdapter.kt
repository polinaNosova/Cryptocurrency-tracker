package com.ua.epam.ctiptocurrencytracker.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ua.epam.ctiptocurrencytracker.fragments.TopGainFragment

class TopGainPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = TopGainFragment()
        fragment.arguments = Bundle().apply {
            putInt("position", position)
            fragment.arguments
        }
        return fragment
    }
}
