package com.justmobiledev.android.spacexinfo.ui.rockets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.justmobiledev.android.spacexinfo.R
import com.justmobiledev.android.spacexinfo.database.models.DbRocket
import com.justmobiledev.android.spacexinfo.databinding.RocketListItemBinding

/**
 * RecyclerView Adapter for setting up data binding on the items in the list.
 */
class RocketListAdapter(val callback: RocketClickListener) : RecyclerView.Adapter<RocketViewHolder>() {

    /**
     * The item that our Adapter will show
     */
    var rocketList: List<DbRocket> = emptyList()
        set(value) {
            field = value

            // Notify any registered observers that the data set has changed.
            notifyDataSetChanged()
        }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketViewHolder {
        val withDataBinding: RocketListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            RocketViewHolder.LAYOUT,
            parent,
            false)
        return RocketViewHolder(withDataBinding)
    }

    override fun getItemCount() = rocketList.size

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     */
    override fun onBindViewHolder(holder: RocketViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.rocket = rocketList[position]
            it.rocketClickCallback = callback
        }
    }
}

/**
 * ViewHolder for DevByte items. All work is done by data binding.
 */
class RocketViewHolder(val viewDataBinding: RocketListItemBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.rocket_list_item
    }
}