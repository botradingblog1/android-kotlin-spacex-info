package com.justmobiledev.android.spacexinfo.ui.crew

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.justmobiledev.android.spacexinfo.R
import com.justmobiledev.android.spacexinfo.databinding.FragmentCrewDetailBinding


class CrewDetailFragment : Fragment() {
    private val viewModel: CrewDetailViewModel by lazy {
        ViewModelProvider(this).get(CrewDetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCrewDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val selectedCrewMember = CrewDetailFragmentArgs.fromBundle(requireArguments()).selectedCrewMember
        viewModel.crewMember.value = selectedCrewMember

        binding.viewModel = viewModel

        // Load crew photo with Glide
        val options: RequestOptions = RequestOptions()
            .placeholder(R.drawable.crew_placeholder)
        Glide.with(this).load(selectedCrewMember.image).apply(options).into(binding.imageviewCrewPhoto)

        return binding.root
    }
}