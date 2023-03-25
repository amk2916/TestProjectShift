package com.example.testprojectshift.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        BankCardInfoModelDB::class
    ], version = 1
)
abstract class BankCardHistoryDataBase : RoomDatabase() {
    abstract fun getBankCardInfoDao(): BankCardInfoDao
}