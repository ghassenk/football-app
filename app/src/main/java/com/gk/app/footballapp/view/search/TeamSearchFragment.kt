package com.gk.app.footballapp.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gk.app.footballapp.R
import com.gk.app.footballapp.presenter.TeamSearchPresenter
import com.gk.app.footballapp.presenter.TeamSearchPresenterImpl
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
        val recyclerView = view.findViewById<RecyclerView>(R.id.search_fragment_recycler)
        val editText = view.findViewById<AutoCompleteTextView>(R.id.search_fragment_edit_text)

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
            recyclerAdapter = TeamItemRecyclerViewAdapter(imageLoader)
            adapter = recyclerAdapter
        }

        return view
    }

    override fun onSearchClicked() {
        TODO("Not yet implemented")
    }

    override fun onListItemClicked() {
        TODO("Not yet implemented")
    }

    override fun onViewDestroyed() {
        TODO("Not yet implemented")
    }

    override fun updateSearchListItems(items: TeamListItem) {
        TODO("Not yet implemented")
    }

    override fun updateAutocompleteList(words: String) {
        TODO("Not yet implemented")
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