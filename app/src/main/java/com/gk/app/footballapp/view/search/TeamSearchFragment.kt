package com.gk.app.footballapp.view.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gk.app.footballapp.BuildConfig
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

    private val logTag = javaClass.simpleName

    @Inject
    lateinit var teamSearchPresenter: TeamSearchPresenter

    @Inject
    lateinit var imageLoader: ImageLoader
    private var columnCount = 1
    private lateinit var autocompleteAdapter: ArrayAdapter<String>
    private lateinit var recyclerAdapter: TeamItemRecyclerViewAdapter
    private var editText: AutoCompleteTextView? = null
    private lateinit var recyclerView: RecyclerView
    override lateinit var errorText: TextView
    override lateinit var progressBar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        if (BuildConfig.DEBUG) {
            Log.v(logTag, "onCreate()")
        }
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onDestroyView() {
        if (BuildConfig.DEBUG) {
            Log.v(logTag, "onDestroyView()")
        }
        super.onDestroyView()

        teamSearchPresenter.onViewDestroyed()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (BuildConfig.DEBUG) {
            Log.v(logTag, "onSaveInstanceState()")
        }
        super.onSaveInstanceState(outState)
        editText?.let {
            if (!it.text.isNullOrEmpty()) {
                outState.putString(SAVED_STATE_KEY_EDIT_TEXT, it.text.toString())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (BuildConfig.DEBUG) {
            Log.v(logTag, "onCreateView() savedInstanceState=$savedInstanceState")
        }
        val view = inflater.inflate(R.layout.fragment_team_search, container, false)
        recyclerView = view.findViewById(R.id.search_fragment_recycler)
        editText = view.findViewById(R.id.search_fragment_edit_text)
        errorText = view.findViewById(R.id.search_fragment_error_text)
        progressBar = view.findViewById(R.id.search_fragment_progress_bar)


        // Set the search edit text
        editText?.let {
            savedInstanceState?.let { bundle ->
                val savedText = bundle.getString(SAVED_STATE_KEY_EDIT_TEXT)
                if (!savedText.isNullOrEmpty()) {
                    it.setText(savedText)
                }
            }
            autocompleteAdapter =
                ArrayAdapter<String>(
                    it.context,
                    android.R.layout.simple_dropdown_item_1line,
                    emptyList()
                )
            it.setAdapter(autocompleteAdapter)

            it.setOnEditorActionListener(object : TextView.OnEditorActionListener {
                override fun onEditorAction(
                    textView: TextView?,
                    actionId: Int,
                    keyEvent: KeyEvent?
                ): Boolean {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        onSearchClicked()
                        return true
                    }
                    return false
                }

            })
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
        editText?.let {
            if (!it.text.isNullOrEmpty()) {
                val keyword = it.text.toString()
                teamSearchPresenter.onSearchClicked(keyword)
            }
        }
    }

    override fun onListItemClicked(item: TeamListItem) {
        teamSearchPresenter.onTeamListItemClicked(item)
    }

    override fun updateSearchListItems(items: List<TeamListItem>) {
        recyclerAdapter.updateItems(items)
    }

    override fun updateAutocompleteList(words: List<String>) {
        autocompleteAdapter.clear()
        autocompleteAdapter.addAll(words)
    }

    override fun disableSearch() {
        editText?.isEnabled = false
    }

    override fun enableSearch() {
        editText?.isEnabled = true
    }

    override fun showError(message: String) {
        recyclerView.visibility = View.GONE
        super.showError(message)
    }

    override fun hideError() {
        recyclerView.visibility = View.VISIBLE
        super.hideError()
    }


    override fun hideKeyboard() {
        editText?.let {
            val imm =
                it.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }


    companion object {

        private const val SAVED_STATE_KEY_EDIT_TEXT = "edit-text"
        private const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            TeamSearchFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }


}