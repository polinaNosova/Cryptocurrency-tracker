package com.ua.epam.ctiptocurrencytracker.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ua.epam.ctiptocurrencytracker.fragments.LossFragment
import com.ua.epam.ctiptocurrencytracker.fragments.TopCoinFragment

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TopCoinFragment()
            else -> LossFragment()
        }
    }
}
