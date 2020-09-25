package com.rsh_engineering.tkachenkoni.notebookapp.framework

import com.rsh_engineering.tkachenkoni.core.usecase.AddRecordBook
import com.rsh_engineering.tkachenkoni.core.usecase.GetAllRecordBook
import com.rsh_engineering.tkachenkoni.core.usecase.GetRecordBook
import com.rsh_engineering.tkachenkoni.core.usecase.RemoveRecordBook

/**
 *
 * Created by Nikolay Tkachenko on 26, September, 2020
 *
 */
data class UseCases(
    val addRecordBook: AddRecordBook,
    val getAllRecordsBook: GetAllRecordBook,
    val getRecordBook: GetRecordBook,
    val removeRecordBook: RemoveRecordBook
)