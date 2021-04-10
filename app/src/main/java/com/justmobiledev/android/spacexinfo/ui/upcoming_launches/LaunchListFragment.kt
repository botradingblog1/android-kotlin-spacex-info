package com.justmobiledev.android.spacexinfo.ui.upcoming_launches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.justmobiledev.android.spacexinfo.R
import com.justmobiledev.android.spacexinfo.database.models.DbLaunch
import com.justmobiledev.android.spacexinfo.databinding.FragmentLaunchListBinding

class LaunchListFragment : Fragment() {
    private var launchListAdapter: LaunchListAdapter? = null

    private val viewModel: LaunchListViewModel by lazy {
        ViewModelProvider(this).get(LaunchListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLaunchListBinding.inflate(inflater)
        binding.lifecycleOwner = this

        launchListAdapter = LaunchListAdapter(LaunchClickListener {
            // Navigate to detail fragment
            this.findNavController().navigate(LaunchListFragmentDirections.actionLaunchListFragmentToLaunchDetailFragment(it))
        })

        binding.root.findViewById<RecyclerView>(R.id.recyclerview_launch_list).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = launchListAdapter
        }

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observer change of Asteroid list in view model and populate adapter
        viewModel.launchList.observe(viewLifecycleOwner, Observer<List<DbLaunch>> { launchList ->
            launchList?.apply {
                launchListAdapter?.launchList = launchList
            }
        })
    }
}