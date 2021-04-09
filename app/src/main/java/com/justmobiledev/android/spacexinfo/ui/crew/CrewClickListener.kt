package com.justmobiledev.android.spacexinfo.ui.crew

import com.justmobiledev.android.spacexinfo.database.models.DbCrew

class CrewClickListener(val block: (DbCrew) -> Unit) {
    /**
     * Called when a Crew card is clicked
     */
    fun onClick(crew: DbCrew) = block(crew)
}