package com.justmobiledev.android.spacexinfo.ui.rockets

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
import com.justmobiledev.android.spacexinfo.database.models.DbRocket
import com.justmobiledev.android.spacexinfo.databinding.FragmentCrewListBinding
import com.justmobiledev.android.spacexinfo.databinding.FragmentRocketListBinding

class RocketListFragment : Fragment() {
    private var rocketListAdapter: RocketListAdapter? = null

    private val viewModel: RocketListViewModel by lazy {
        ViewModelProvider(this).get(RocketListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRocketListBinding.inflate(inflater)
        binding.lifecycleOwner = this

        rocketListAdapter = RocketListAdapter(RocketClickListener {
            // Navigate to detail fragment
            this.findNavController().navigate(RocketListFragmentDirections.actionRocketListFragmentToRocketDetailFragment(it))
        })

        binding.root.findViewById<RecyclerView>(R.id.recyclerview_rocket_list).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = rocketListAdapter
        }

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observer change of Asteroid list in view model and populate adapter
        viewModel.rocketList.observe(viewLifecycleOwner, Observer<List<DbRocket>> { rocketList ->
            rocketList?.apply {
                rocketListAdapter?.rocketList = rocketList
            }
        })
    }
}