package com.gk.app.footballapp.view

interface BasePresenter {
    var isViewDestroyed: Boolean

    fun onViewDestroyed() {
        isViewDestroyed = true
    }
}