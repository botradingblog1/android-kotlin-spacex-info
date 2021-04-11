package com.justmobiledev.android.spacexinfo.ui.crew

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.justmobiledev.android.spacexinfo.R
import com.justmobiledev.android.spacexinfo.database.models.DbCrew
import com.justmobiledev.android.spacexinfo.databinding.CrewListItemBinding

/**
 * RecyclerView Adapter for setting up data binding on the items in the list.
 */
class CrewListAdapter(val callback: CrewClickListener) : RecyclerView.Adapter<CrewViewHolder>() {

    /**
     * The item that our Adapter will show
     */
    var crewList: List<DbCrew> = emptyList()
        set(value) {
            field = value

            // Notify any registered observers that the data set has changed.
            notifyDataSetChanged()
        }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrewViewHolder {
        val withDataBinding: CrewListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CrewViewHolder.LAYOUT,
            parent,
            false)
        return CrewViewHolder(withDataBinding)
    }

    override fun getItemCount() = crewList.size

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     */
    override fun onBindViewHolder(holder: CrewViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.crew = crewList[position]
            it.crewClickCallback = callback
        }
    }
}

/**
 * ViewHolder for crew items. All work is done by data binding.
 */
class CrewViewHolder(val viewDataBinding: CrewListItemBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.crew_list_item
    }
}