package com.gk.app.football.data.internal

import com.gk.app.football.data.entity.TeamImpl
import com.google.gson.annotations.SerializedName

internal data class TeamSearchResponse(
    @SerializedName("teams")
    val teams: List<TeamImpl>
)
