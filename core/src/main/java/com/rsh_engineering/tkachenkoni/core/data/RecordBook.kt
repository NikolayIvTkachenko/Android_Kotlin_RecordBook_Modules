package com.rsh_engineering.tkachenkoni.core.data

/**
 *
 * Created by Nikolay Tkachenko on 26, September, 2020
 *
 */
data class RecordBook(
    var title: String,
    var content: String,
    var creationTime: Long,
    var updateTime: Long,
    var id: Long = 0
)