package com.steve_md.youtubeapp.ui.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.steve_md.youtubeapp.adapter.YoutubeVideoAdapter
import com.steve_md.youtubeapp.databinding.FragmentHomeBinding
import com.steve_md.youtubeapp.viewmodel.KenyanYoutubeVideosViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding:  FragmentHomeBinding
    private lateinit var youtubeVideoAdapter: YoutubeVideoAdapter
    private val youtubeVideosViewModel: KenyanYoutubeVideosViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(
            inflater,
            container,
            false
        )
        hideActionBar()
        return binding.root
    }

    private fun hideActionBar() {
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpYoutubeVideoRecyclerView()
        youtubeVideosObserver()
    }

    private fun youtubeVideosObserver() {
        youtubeVideosViewModel.kenyanYoutubeVideosViewModel.observe(viewLifecycleOwner) {
            youtubeVideoAdapter.submitList(it.data?.items)
        }
    }

    private fun setUpYoutubeVideoRecyclerView() {
        youtubeVideoAdapter = YoutubeVideoAdapter()
        binding.youtubeVideosRecyclerView.adapter = youtubeVideoAdapter
        binding.youtubeVideosRecyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding
    }


}