package com.example.testprojectshift.data.server

data class BankInfoServerModel(
    val number: Number,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    val country: Country,
    val bank: Bank,
)