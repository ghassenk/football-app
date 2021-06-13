package com.gk.app.footballapp.view.search

import com.gk.app.footballapp.view.BaseView

interface TeamSearchView : BaseView {

    //region View Events
    fun onSearchClicked()
    fun onListItemClicked(item: TeamListItem)
    //endregion

    //region View Updates
    fun updateSearchListItems(items: List<TeamListItem>)
    fun updateAutocompleteList(words: List<String>)
    fun disableSearchBar()
    fun enableSearchBar()
    fun hideKeyboard()
    //endregion

}