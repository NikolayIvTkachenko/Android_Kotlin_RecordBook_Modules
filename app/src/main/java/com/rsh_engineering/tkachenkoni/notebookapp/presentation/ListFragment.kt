package com.rsh_engineering.tkachenkoni.notebookapp.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.rsh_engineering.tkachenkoni.notebookapp.R
import com.rsh_engineering.tkachenkoni.notebookapp.framework.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_record.*

/**
 *
 * Created by Nikolay Tkachenko on 26, September, 2020
 *
 */
class ListFragment : Fragment() , ListAction{

    private val recordBookListAdapter = RecordsBookListAdapter(arrayListOf(),this)
    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recordsListView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recordBookListAdapter
        }

        addRecordBook.setOnClickListener{goToRecordBookDetails()}

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)

        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.recordsBook.observe(viewLifecycleOwner, Observer {recordBookList ->
            loadingView.visibility = View.GONE
            recordsListView.visibility = View.VISIBLE
            recordBookListAdapter.updateNotes(recordBookList.sortedByDescending {it.updateTime })
        })
    }

    private fun goToRecordBookDetails(id: Long = 0L){
        val action = ListFragmentDirections.actionGoToRecord(id)

        Navigation.findNavController(recordsListView).navigate(action)
    }

    private fun hideKeyboard(){
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(titleView.windowToken, 0)
    }

    override fun onResume() {
        super.onResume()

        viewModel.getNotes()
    }

    override fun onClick(id: Long) {
        goToRecordBookDetails(id)
    }
}