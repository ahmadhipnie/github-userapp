package com.example.submission_awal_fundamental.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission_awal_fundamental.databinding.ActivityMainBinding
import com.example.submission_awal_fundamental.ui.adapter.UserAdapter
import com.example.submission_awal_fundamental.ui.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var mainViewModel: MainViewModel

    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)

        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        userAdapter = UserAdapter()

        binding.rvUsers.layoutManager = layoutManager
        binding.rvUsers.addItemDecoration(itemDecoration)
        binding.rvUsers.adapter = userAdapter


        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        mainViewModel.showUsers()

        binding.progressBar.visibility = View.GONE

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView.editText.setOnEditorActionListener { textView, actionId, event ->
                searchBar.setText(searchView.text)
                searchView.hide()
                showLoading(true)
                val query = binding.searchBar.text.toString()
                mainViewModel.showSearchedUsers(query)
                false
            }
        }

        mainViewModel.users.observe(this) { users ->
            userAdapter.setData(users)
            userAdapter.notifyDataSetChanged()
        }


    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}