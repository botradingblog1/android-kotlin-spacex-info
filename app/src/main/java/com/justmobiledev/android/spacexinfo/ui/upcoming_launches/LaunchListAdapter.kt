package com.justmobiledev.android.spacexinfo.ui.upcoming_launches

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.justmobiledev.android.spacexinfo.R
import com.justmobiledev.android.spacexinfo.database.models.DbLaunch
import com.justmobiledev.android.spacexinfo.databinding.LaunchListItemBinding

/**
 * RecyclerView Adapter for setting up data binding on the items in the list.
 */
class LaunchListAdapter(val callback: LaunchClickListener) : RecyclerView.Adapter<LaunchViewHolder>() {

    /**
     * The item that our Adapter will show
     */
    var launchList: List<DbLaunch> = emptyList()
        set(value) {
            field = value

            // Notify any registered observers that the data set has changed.
            notifyDataSetChanged()
        }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        val withDataBinding: LaunchListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            LaunchViewHolder.LAYOUT,
            parent,
            false)
        return LaunchViewHolder(withDataBinding)
    }

    override fun getItemCount() = launchList.size

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     */
    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.launch = launchList[position]
            it.launchClickCallback = callback
        }
    }
}

/**
 * ViewHolder for launch items. All work is done by data binding.
 */
class LaunchViewHolder(val viewDataBinding: LaunchListItemBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.launch_list_item
    }
}