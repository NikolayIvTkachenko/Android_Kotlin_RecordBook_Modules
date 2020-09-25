package com.rsh_engineering.tkachenkoni.notebookapp.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.rsh_engineering.tkachenkoni.core.data.RecordBook
import com.rsh_engineering.tkachenkoni.core.repository.RecordBookRepository
import com.rsh_engineering.tkachenkoni.core.usecase.AddRecordBook
import com.rsh_engineering.tkachenkoni.core.usecase.GetAllRecordBook
import com.rsh_engineering.tkachenkoni.core.usecase.GetRecordBook
import com.rsh_engineering.tkachenkoni.core.usecase.RemoveRecordBook
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
/**
 *
 * Created by Nikolay Tkachenko on 26, September, 2020
 *
 */
class ListViewModel(application: Application):AndroidViewModel(application) {

    private val coroutineScope = CoroutineScope((Dispatchers.IO))

    val repository = RecordBookRepository(RoomRecordBookDataSource(application))

    val useCases = UseCases(
        AddRecordBook(repository),
        GetAllRecordBook(repository),
        GetRecordBook(repository),
        RemoveRecordBook(repository)
    )

    val recordsBook = MutableLiveData<List<RecordBook>>()

    fun getNotes(){
        coroutineScope.launch {
            val noteList = useCases.getAllRecordsBook()
            recordsBook.postValue(noteList)
        }
    }
}