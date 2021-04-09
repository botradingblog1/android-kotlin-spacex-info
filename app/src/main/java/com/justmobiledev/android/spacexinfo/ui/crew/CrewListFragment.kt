package com.justmobiledev.android.spacexinfo.ui.crew

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.justmobiledev.android.spacexinfo.databinding.FragmentCrewBinding
import com.justmobiledev.android.spacexinfo.databinding.FragmentCrewListBinding

class CrewListFragment : Fragment() {

    private val viewModel: CrewListViewModel by lazy {
        ViewModelProvider(this).get(CrewListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCrewListBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        return binding.root
    }
}