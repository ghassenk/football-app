package com.gk.app.footballapp.view

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView

interface BaseView {
    var errorText: TextView
    var progressBar: ProgressBar

    fun showError(message: String) {
        progressBar.visibility = View.GONE
        errorText.visibility = View.VISIBLE
        errorText.text = message
    }

    fun hideError() {
        errorText.visibility = View.GONE
    }

    fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgress() {
        progressBar.visibility = View.GONE
    }
}