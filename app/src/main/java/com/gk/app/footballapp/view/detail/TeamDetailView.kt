package com.gk.app.footballapp.view.detail

interface TeamDetailView {
    fun onViewDestroyed()//?

    fun updateDetails(imageUrl: String, title: String, description: String)
}