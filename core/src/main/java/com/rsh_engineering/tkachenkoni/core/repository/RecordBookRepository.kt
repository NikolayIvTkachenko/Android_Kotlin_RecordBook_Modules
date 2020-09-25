package com.rsh_engineering.tkachenkoni.core.repository

import com.rsh_engineering.tkachenkoni.core.data.RecordBook

/**
 *
 * Created by Nikolay Tkachenko on 26, September, 2020
 *
 */
class RecordBookRepository(private val dataSource: RecordBookDataSource){

    suspend fun addNote(recordBook: RecordBook) = dataSource.add(recordBook)

    suspend fun getNote(id: Long) = dataSource.get(id)

    suspend fun getAllNotes() = dataSource.getAll()

    suspend fun removeNote(recordBook: RecordBook) = dataSource.remove(recordBook)

}