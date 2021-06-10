package com.gk.app.footballapp.view.search

interface TeamSearchView {

    fun onSearchClicked()
    fun onListItemClicked()
    fun onViewDestroyed()//?

    fun updateSearchListItems(items: TeamListItem)
    fun updateAutocompleteList(words: String)
}