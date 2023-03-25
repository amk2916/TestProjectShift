package com.example.testprojectshift.domain

import com.example.testprojectshift.data.server.Bank
import com.example.testprojectshift.data.server.Country
import com.example.testprojectshift.data.server.Number

data class BankCardInfo (
    val numberCardInQuery: String,
    val schemeText: String,
    val brandText: String,
    val number: Number,
    val typeText: String,
    val prepaidText: String,
    val country: Country,
    val bank: Bank
)