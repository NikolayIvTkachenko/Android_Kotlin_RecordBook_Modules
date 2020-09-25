package com.rsh_engineering.tkachenkoni.core.usecase

import com.rsh_engineering.tkachenkoni.core.data.RecordBook
import com.rsh_engineering.tkachenkoni.core.repository.RecordBookRepository

/**
 *
 * Created by Nikolay Tkachenko on 26, September, 2020
 *
 */

class GetRecordBook(private val recordBookRepository: RecordBookRepository){

    suspend operator fun invoke(id: Long) = recordBookRepository.getNote(id)

}