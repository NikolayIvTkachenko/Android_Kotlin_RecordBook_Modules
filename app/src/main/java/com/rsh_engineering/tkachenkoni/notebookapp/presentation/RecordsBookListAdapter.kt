package com.rsh_engineering.tkachenkoni.notebookapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rsh_engineering.tkachenkoni.core.data.RecordBook
import com.rsh_engineering.tkachenkoni.notebookapp.R
import kotlinx.android.synthetic.main.item_record.view.*
import java.sql.Date
import java.text.SimpleDateFormat

/**
 *
 * Created by Nikolay Tkachenko on 26, September, 2020
 *
 */
class RecordsBookListAdapter(var records: ArrayList<RecordBook>, val actions: ListAction): RecyclerView.Adapter<RecordsBookListAdapter.NoteViewHolder>() {

    fun updateNotes(newRecordBook: List<RecordBook>){
        records.clear()
        records.addAll(newRecordBook)
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val lauoyt = view.recordBookLayout
        private val recordBookTitle = view.title
        private val recordBookContent = view.content
        private val recordBookDate = view.date

        fun bind(recordBook: RecordBook) {
            recordBookTitle.text = recordBook.title
            recordBookContent.text = recordBook.content

            val sdf = SimpleDateFormat("MMM dd, HH:mm:ss")
            val resultDate = Date(recordBook.updateTime)
            recordBookDate.text = "Last updated: ${sdf.format(resultDate)}"

            lauoyt.setOnClickListener {
                actions.onClick(recordBook.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder =NoteViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_record, parent, false)
    )

    override fun getItemCount(): Int = records.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(records[position])
    }

}