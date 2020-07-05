package com.example.ourapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter (fragmentActivity: FragmentActivity):
    FragmentStateAdapter(fragmentActivity) {
        private val JUMLAH_MENU = 4
        override fun createFragment(position: Int): Fragment {
            when (position) {
                0 -> { return Indonesia() }
                1 -> { return Inform() }
                2 -> { return News() }
                3 -> { return Home() }
                else -> {
                    return Home()
                }
            }
        }
        override fun getItemCount(): Int {
            return JUMLAH_MENU
        }
}