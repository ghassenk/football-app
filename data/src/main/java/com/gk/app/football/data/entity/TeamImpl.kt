package com.gk.app.football.data.entity

import com.gk.app.football.domain.entity.Team
import com.google.gson.annotations.SerializedName

internal data class TeamImpl(

    @SerializedName("strTeam")
    override var name: String,
    @SerializedName("strTeamBanner")
    override var bannerUrl: String?,
    @SerializedName("strTeamBadge")
    override var badgeUrl: String?,
    @SerializedName("strCountry")
    override var country: String?,
    @SerializedName("strLeague")
    override var league: String?,
    @SerializedName("strDescriptionEN")
    override var description: String?,

    ) : Team
