package com.gk.app.footballapp.view.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gk.app.footballapp.R
import com.gk.app.footballapp.presenter.TeamSearchPresenter
import com.gk.app.footballapp.view.image.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 */
@AndroidEntryPoint
class TeamSearchFragment : Fragment(), TeamSearchView {

    @Inject
    lateinit var teamSearchPresenter: TeamSearchPresenter

    @Inject
    lateinit var imageLoader: ImageLoader
    private var columnCount = 1
    private lateinit var autocompleteAdapter: ArrayAdapter<String>
    private lateinit var recyclerAdapter: TeamItemRecyclerViewAdapter
    private lateinit var editText: AutoCompleteTextView
    private lateinit var searchButton: ImageButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var errorText: TextView
    private lateinit var progressBar: ProgressBar

    // TODO add progress bar and block multiple search

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_team_search, container, false)
        recyclerView = view.findViewById(R.id.search_fragment_recycler)
        editText = view.findViewById(R.id.search_fragment_edit_text)
        searchButton = view.findViewById(R.id.search_fragment_button)
        errorText = view.findViewById(R.id.search_fragment_error_text)
        progressBar = view.findViewById(R.id.search_fragment_progress_bar)

        // Set button click listener
        searchButton.setOnClickListener {
            if (!editText.text.isNullOrBlank()) {
                onSearchClicked()
            }
        }

        // Set the autocomplete search edit text
        with(editText) {
            autocompleteAdapter =
                ArrayAdapter<String>(
                    context,
                    android.R.layout.simple_dropdown_item_1line,
                    emptyList()
                )
            setAdapter(autocompleteAdapter)
        }

        // Set the recycler view
        with(recyclerView) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            recyclerAdapter = TeamItemRecyclerViewAdapter(imageLoader) { onListItemClicked(it) }
            adapter = recyclerAdapter
        }

        return view
    }

    override fun onSearchClicked() {
        val keyword = editText.text.toString()
        teamSearchPresenter.onSearchClicked(keyword)
    }

    override fun onListItemClicked(item: TeamListItem) {
        teamSearchPresenter.onTeamListItemClicked(item)
    }

    override fun onViewDestroyed() {
        teamSearchPresenter.onViewDestroyed()
    }

    override fun updateSearchListItems(items: List<TeamListItem>) {
        recyclerAdapter.updateItems(items)
    }

    override fun updateAutocompleteList(words: List<String>) {
        autocompleteAdapter.clear()
        autocompleteAdapter.addAll(words)
    }

    override fun disableSearch() {
        searchButton.isEnabled = false
        editText.isEnabled = false
    }

    override fun enableSearch() {
        searchButton.isEnabled = true
        editText.isEnabled = true
    }

    override fun showError(message: String) {
        recyclerView.visibility = View.GONE
        progressBar.visibility = View.GONE
        errorText.visibility = View.VISIBLE
        errorText.text = message
    }

    override fun hideError() {
        recyclerView.visibility = View.VISIBLE
        errorText.visibility = View.GONE
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun hideKeyboard() {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.hideSoftInputFromWindow(editText.windowToken, 0)
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            TeamSearchFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }


}