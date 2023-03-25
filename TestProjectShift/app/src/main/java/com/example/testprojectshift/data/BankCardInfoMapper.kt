package com.example.testprojectshift.data

import com.example.testprojectshift.data.db.BankCardInfoModelDB
import com.example.testprojectshift.data.server.Bank
import com.example.testprojectshift.data.server.BankInfoServerModel
import com.example.testprojectshift.data.server.Country
import com.example.testprojectshift.data.server.Number
import com.example.testprojectshift.domain.BankCardInfo

fun BankInfoServerModel.toDomainModel(numberCardEnter: String) = BankCardInfo(
    numberCardInQuery = numberCardEnter,
    schemeText = scheme ?: "Неизвестно",
    brandText = brand ?: "Неизвестно",
    number = number,
    typeText = type ?: "Неизвестно",
    prepaidText = if(prepaid == true) "Yes" else "No",
    country = country,
    bank = bank
)

fun BankInfoServerModel.toLocalModel(numberCardEnter: String) = BankCardInfoModelDB(
    numberCardInQuery = numberCardEnter,
    numberLength = number.length ?: 0,
    numberLuhn = number.luhn ?: false,
    scheme = scheme?: "Неизвестно",
    type = type ?: "Неизвестно",
    brand = brand ?: "Неизвестно",
    prepaid = prepaid ?: false,
    numericCountry = country.numeric ?: "Неизвестно",
    alpha2Country = country.alpha2 ?: "Неизвестно",
    nameCountry = country.name ?: "Неизвестно",
    emojiCountry = country.emoji ?: "Неизвестно",
    currencyCountry = country.currency ?: "Неизвестно",
    latitudeCountry = country.latitude ?: 0,
    longitudeCountry = country.longitude ?: 0,
    nameBank = bank.name ?: "Неизвестно",
    urlBank = bank.url ?: "Неизвестно",
    phoneBank = bank.phone ?: "Неизвестно",
    cityBank = bank.city ?: "Неизвестно"
)

fun BankCardInfoModelDB.localToDomainModel() = BankCardInfo(
    numberCardInQuery = numberCardInQuery,
    schemeText = scheme,
    brandText = brand,
    number = Number(numberLength,numberLuhn),
    typeText = type,
    prepaidText = if(prepaid) "Yes" else "No",
    country = Country(
        numericCountry,
        alpha2Country,
        nameCountry,
        emojiCountry,
        currencyCountry,
        latitudeCountry,
        longitudeCountry
    ),
    bank = Bank(
        nameBank,
        urlBank,
        phoneBank,
        cityBank
    )
)
