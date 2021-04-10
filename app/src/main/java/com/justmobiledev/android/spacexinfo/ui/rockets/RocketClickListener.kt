package com.justmobiledev.android.spacexinfo.ui.rockets


import com.justmobiledev.android.spacexinfo.database.models.DbRocket

class RocketClickListener(val block: (DbRocket) -> Unit) {
    /**
     * Called when a card is clicked
     */
    fun onClick(rocket: DbRocket) = block(rocket)
}