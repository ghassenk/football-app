package com.gk.app.football.domain.entity

interface Team {
    var name: String
    var bannerUrl: String?
    var badgeUrl: String?
    var country: String?
    var league: String?
    var description: String?
}