package com.rsh_engineering.tkachenkoni.notebookapp.framework

import android.content.Context
import com.rsh_engineering.tkachenkoni.core.data.RecordBook
import com.rsh_engineering.tkachenkoni.core.repository.RecordBookDataSource
import com.rsh_engineering.tkachenkoni.notebookapp.framework.db.DatabaseService
import com.rsh_engineering.tkachenkoni.notebookapp.framework.db.RecordBookEntity

/**
 *
 * Created by Nikolay Tkachenko on 26, September, 2020
 *
 */
class RoomRecordBookDataSource (context: Context): RecordBookDataSource {

    val noteDao = DatabaseService.getInstance(context).recordBookDao()

    override suspend fun add(recordBook: RecordBook) = noteDao.addRecordBookEntity(RecordBookEntity.fromRecordBook(recordBook))

    override suspend fun get(id: Long): RecordBook? = noteDao.getRecordBookEntity(id)?.toRecordBook()

    override suspend fun getAll(): List<RecordBook> = noteDao.getAllRecordBookEntities().map { it.toRecordBook() }

    override suspend fun remove(recordBook: RecordBook) = noteDao.deleteRecordBookEntity(RecordBookEntity.fromRecordBook(recordBook))
}