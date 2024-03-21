package com.example.submission_awal_fundamental.ui.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.submission_awal_fundamental.ui.fragment.FollowFragment

class SectionsPagerAdapter(activity: AppCompatActivity) :FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {

        val fragment = FollowFragment()
        val bundle = Bundle()
        val index= position + 1
        bundle.putInt(FollowFragment.ARG_SECTION_NUMBER, index)

        fragment.arguments = bundle
        return fragment

    }
}