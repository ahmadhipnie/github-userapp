package com.example.submission_awal_fundamental.ui

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.submission_awal_fundamental.R
import com.example.submission_awal_fundamental.databinding.ActivityDetailUserBinding
import com.example.submission_awal_fundamental.ui.adapter.SectionsPagerAdapter
import com.example.submission_awal_fundamental.ui.viewmodel.DetailUserViewModel
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {


    private lateinit var binding : ActivityDetailUserBinding

    private lateinit var detailUserViewModel: DetailUserViewModel

    companion object{

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2,
        )


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra("id" , 0)
        val username = intent.getStringExtra("username")


        detailUserViewModel = ViewModelProvider(this).get(DetailUserViewModel::class.java)

        detailUserViewModel.getDetail(username)


        detailUserViewModel.detailUser.observe(this){
            user -> binding.tvIdAndNameDetail.text = "ID : " + user.id.toString() + " - " + user.login
            binding.tvNamaUserDetail.text = user.name
            Glide.with(this)
                .load(user.avatarUrl)
                .into(binding.ivImageUserDetail)
            binding.tvFollowingUserDetail.text =  "Following : "+user.following.toString()
            binding.tvFollowerUserDetail.text =  "Followers : " +user.followers.toString()
        }


        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter

        val tabs = binding.tabLayout

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f



    }
}