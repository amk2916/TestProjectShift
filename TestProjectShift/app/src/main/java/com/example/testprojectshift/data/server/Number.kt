package com.example.testprojectshift.data.server

import com.example.testprojectshift.data.server.Number

data class Number(
    val length: Long?,
    val luhn: Boolean?,
)

fun Number.defaultIfErr(): Number{
    return com.example.testprojectshift.data.server.Number(
        length = 0,
        luhn = false
    )
}
