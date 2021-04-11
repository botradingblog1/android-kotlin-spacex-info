package com.justmobiledev.android.spacexinfo.ui.upcoming_launches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.justmobiledev.android.spacexinfo.R
import com.justmobiledev.android.spacexinfo.databinding.FragmentLaunchDetailBinding



class LaunchDetailFragment : Fragment() {
    private val viewModel: LaunchDetailViewModel by lazy {
        ViewModelProvider(this).get(LaunchDetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLaunchDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val selectedLaunchMember = LaunchDetailFragmentArgs.fromBundle(requireArguments()).selectedLaunch
        viewModel.launch.value = selectedLaunchMember

        binding.viewModel = viewModel

        // Load launch photo with Glide
        val options: RequestOptions = RequestOptions()
            .placeholder(R.drawable.launch_placeholder)
        Glide.with(this).load(selectedLaunchMember.image).apply(options).into(binding.imageviewLaunchPhoto)

        return binding.root
    }
}