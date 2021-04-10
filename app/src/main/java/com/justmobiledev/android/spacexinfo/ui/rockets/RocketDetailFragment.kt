package com.justmobiledev.android.spacexinfo.ui.rockets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.justmobiledev.android.spacexinfo.R
import com.justmobiledev.android.spacexinfo.databinding.FragmentRocketDetailBinding
import com.justmobiledev.android.spacexinfo.ui.rockets.RocketDetailFragmentArgs


class RocketDetailFragment : Fragment() {
    private val viewModel: RocketDetailViewModel by lazy {
        ViewModelProvider(this).get(RocketDetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRocketDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val selectedRocketMember = RocketDetailFragmentArgs.fromBundle(requireArguments()).selectedRocket
        viewModel.rocket.value = selectedRocketMember

        binding.viewModel = viewModel

        // Load rocket photo
        val options: RequestOptions = RequestOptions()
            .placeholder(R.drawable.rocket_placeholder)

        Glide.with(this).load(selectedRocketMember.image).apply(options).into(binding.imageviewRocketPhoto)

        return binding.root
    }
}