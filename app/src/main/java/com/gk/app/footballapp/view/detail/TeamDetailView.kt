package com.gk.app.footballapp.view.detail

import com.gk.app.footballapp.view.BaseView

interface TeamDetailView : BaseView {

    fun updateDetails(
        bannerUrl: String?,
        teamName: String?,
        countryName: String?,
        leagueName: String?,
        description: String?,
    )
}