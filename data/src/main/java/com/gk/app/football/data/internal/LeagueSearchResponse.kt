package com.gk.app.football.data.internal

import com.gk.app.football.data.entity.LeagueImpl
import com.gk.app.football.data.entity.TeamImpl
import com.google.gson.annotations.SerializedName

internal data class LeagueSearchResponse(
    @SerializedName("leagues")
    val leagues: List<LeagueImpl>
)
