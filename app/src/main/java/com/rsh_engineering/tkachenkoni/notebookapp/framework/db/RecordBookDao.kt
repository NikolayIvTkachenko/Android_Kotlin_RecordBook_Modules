package com.rsh_engineering.tkachenkoni.notebookapp.framework.db

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Dao


/**
 *
 * Created by Nikolay Tkachenko on 26, September, 2020
 *
 */
@Dao
interface RecordBookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecordBookEntity(recordBookEntity: RecordBookEntity)

    @Query("SELECT * FROM records WHERE id = :id")
    suspend fun getRecordBookEntity(id: Long): RecordBookEntity?

    @Query("SELECT * FROM records")
    suspend fun getAllRecordBookEntities():List<RecordBookEntity>

    @Delete
    suspend fun deleteRecordBookEntity(recordBookEntity: RecordBookEntity)
}