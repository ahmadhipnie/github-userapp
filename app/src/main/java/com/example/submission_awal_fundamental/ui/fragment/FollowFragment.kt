package com.example.submission_awal_fundamental.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission_awal_fundamental.databinding.FragmentFollowBinding
import com.example.submission_awal_fundamental.ui.adapter.UserAdapter
import com.example.submission_awal_fundamental.ui.viewmodel.FollowViewModel

class FollowFragment : Fragment() {

    companion object {

        val ARG_SECTION_NUMBER = "section_number"

    }

    private lateinit var viewModel: FollowViewModel

    private val userAdapter = UserAdapter()

    private lateinit var username: String

    private var sectionNumber = 0
    private var _binding: FragmentFollowBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sectionNumber = arguments?.getInt(ARG_SECTION_NUMBER) ?: 0
        username = requireActivity().intent.extras?.getString("username").toString()


        viewModel = ViewModelProvider(this).get(FollowViewModel::class.java)
        viewModel.users.observe(viewLifecycleOwner) { following ->
            userAdapter.setData(following)
        }


        binding.rvFollowing.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFollowing.adapter = userAdapter


        when (sectionNumber) {
            1 -> {
                binding.tvNamaUser.text = "Following"
                viewModel.showFollowing(username)
                viewModel.users.observe(viewLifecycleOwner) { followers ->
                    userAdapter.setData(followers)
                }
                binding.progressBar.visibility = View.GONE
            }
            2 -> {
                binding.tvNamaUser.text = "Followers"
                viewModel.showFollowers(username)
                viewModel.users.observe(viewLifecycleOwner) { followers ->
                    userAdapter.setData(followers)
                }
                binding.progressBar.visibility = View.GONE
            }
        }
    }
}