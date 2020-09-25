package com.rsh_engineering.tkachenkoni.notebookapp.presentation

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.rsh_engineering.tkachenkoni.core.data.RecordBook
import com.rsh_engineering.tkachenkoni.notebookapp.R
import com.rsh_engineering.tkachenkoni.notebookapp.framework.RecordBookViewModel
import kotlinx.android.synthetic.main.fragment_record.*

/**
 *
 * Created by Nikolay Tkachenko on 26, September, 2020
 *
 */
class RecordBookFragment : Fragment() {

    private var recordBookId = 0L
    private lateinit var viewModel: RecordBookViewModel
    private var currentRecordBook = RecordBook("", "", 0L, 0L)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_record, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(RecordBookViewModel::class.java)

        arguments?.let {
            recordBookId = RecordBookFragmentArgs.fromBundle(it).recordId
        }

        if(recordBookId != 0L){
            viewModel.getRecordBook(recordBookId)
        }

        checkButton.setOnClickListener {

            if(titleView.text.toString() != "" || contentView.text.toString() != ""){
                val time = System.currentTimeMillis()
                currentRecordBook.title = titleView.text.toString()
                currentRecordBook.content = contentView.text.toString()
                currentRecordBook.updateTime = time

                if(currentRecordBook.id == 0L){
                    currentRecordBook.creationTime = time
                }

                viewModel.saveRecordBook(currentRecordBook)

            }else{

                Navigation.findNavController(it).popBackStack()
            }
        }

        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.saved.observe(viewLifecycleOwner, Observer {
            if(it) {
                Toast.makeText(context, "Done!", Toast.LENGTH_LONG).show()
                hideKeyboard()
                Navigation.findNavController(titleView).popBackStack()
            }else{
                Toast.makeText(context, "Something went wrong, please try again", Toast.LENGTH_LONG).show()
            }
        })

        viewModel.currentRecordBook.observe(viewLifecycleOwner, Observer { recordBook ->
            recordBook?.let {
                currentRecordBook = it
                titleView.setText(it.title, TextView.BufferType.EDITABLE)
                contentView.setText(it.content, TextView.BufferType.EDITABLE)
            }
        })
    }

    private fun hideKeyboard(){
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(titleView.windowToken, 0)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.record_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.deleteRecordBook ->{
                if(context != null && recordBookId != 0L){
                    AlertDialog.Builder(context!!)
                        .setTitle("Delete Note")
                        .setMessage("Are you sure you want to delete this note?")
                        .setPositiveButton("Yes"){dialogInterface, i ->
                            viewModel.deleteNote(currentRecordBook)
                        }
                        .setNegativeButton("Cancel"){dialogInterface, i -> }
                        .create()
                        .show()
                }
            }
        }
        return true
    }

}