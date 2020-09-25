package com.rsh_engineering.tkachenkoni.notebookapp.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rsh_engineering.tkachenkoni.core.data.RecordBook

/**
 *
 * Created by Nikolay Tkachenko on 26, September, 2020
 *
 */

@Entity(tableName = "records")
class RecordBookEntity (
    val title: String,
    val content:String,

    @ColumnInfo(name="creation_date")
    val creationTime:Long,

    @ColumnInfo(name = "update_time")
    val updateTime:Long,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L

) {
    companion object{
        fun fromRecordBook(recordBook: RecordBook) = RecordBookEntity (recordBook.title, recordBook.content, recordBook.creationTime, recordBook.updateTime, recordBook.id)
    }

    fun toRecordBook() = RecordBook(title, content, creationTime, updateTime, id)

}