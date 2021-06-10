package com.gk.app.football.data.entity

import com.gk.app.football.domain.entity.Team
import com.google.gson.annotations.SerializedName

internal data class TeamImpl(

    @SerializedName("strTeam")
    override val name: String,
    override val bannerUrl: String,
    override val thumbUrl: String,
    override val country: String,
    override val league: String,
    override val description: String

) : Team
