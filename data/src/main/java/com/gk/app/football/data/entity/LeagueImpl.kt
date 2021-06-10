package com.gk.app.football.data.entity

import com.gk.app.football.domain.entity.League
import com.google.gson.annotations.SerializedName

internal data class LeagueImpl(

    @SerializedName("strLeague")
    override val name: String,

) : League
