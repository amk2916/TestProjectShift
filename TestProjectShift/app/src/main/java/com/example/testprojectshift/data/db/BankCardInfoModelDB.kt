package com.example.testprojectshift.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_query")
data class BankCardInfoModelDB(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val numberCardInQuery: String,
    val numberLength: Long,
    val numberLuhn: Boolean,
    val scheme: String,
    val type: String,
    val brand: String,
    val prepaid: Boolean,
    val numericCountry: String,
    val alpha2Country: String,
    val nameCountry: String,
    val emojiCountry: String,
    val currencyCountry: String,
    val latitudeCountry: Long,
    val longitudeCountry: Long,
    val nameBank: String,
    val urlBank: String,
    val phoneBank: String,
    val cityBank: String
)
