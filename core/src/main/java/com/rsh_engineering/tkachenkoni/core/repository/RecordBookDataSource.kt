package com.rsh_engineering.tkachenkoni.core.repository

import com.rsh_engineering.tkachenkoni.core.data.RecordBook

/**
 *
 * Created by Nikolay Tkachenko on 26, September, 2020
 *
 */
interface RecordBookDataSource {

    suspend fun add(record: RecordBook)

    suspend fun get(id: Long):RecordBook?

    suspend fun getAll(): List<RecordBook>

    suspend fun remove(record: RecordBook)

}