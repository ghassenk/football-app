package com.gk.app.footballapp.view.search

interface TeamSearchView {

    fun onSearchClicked()
    fun onListItemClicked(item: TeamListItem)
    fun onViewDestroyed()
    //fun onBack()

    fun updateSearchListItems(items: List<TeamListItem>)
    fun updateAutocompleteList(words: List<String>)
    fun disableSearch()
    fun enableSearch()
    fun showError(message: String)
    fun hideError()
    fun showProgress()
    fun hideProgress()
    fun hideKeyboard()

}