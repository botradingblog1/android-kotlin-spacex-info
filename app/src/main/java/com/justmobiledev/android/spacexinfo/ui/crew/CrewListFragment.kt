package com.justmobiledev.android.spacexinfo.ui.crew

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.justmobiledev.android.spacexinfo.R
import com.justmobiledev.android.spacexinfo.database.models.DbCrew
import com.justmobiledev.android.spacexinfo.databinding.FragmentCrewListBinding

class CrewListFragment : Fragment() {
    private var crewListAdapter: CrewListAdapter? = null

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

        crewListAdapter = CrewListAdapter(CrewClickListener {
            // Navigate to the detail fragment
            // todo this.findNavController().navigate(MainFragmentDirections.actionShowDetail(it))
        })

        binding.root.findViewById<RecyclerView>(R.id.recyclerview_crew_list).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = crewListAdapter
        }

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observer change of Asteroid list in view model and populate adapter
        viewModel.crewList.observe(viewLifecycleOwner, Observer<List<DbCrew>> { crewList ->
            crewList?.apply {
                crewListAdapter?.crewList = crewList
            }
        })
    }
}