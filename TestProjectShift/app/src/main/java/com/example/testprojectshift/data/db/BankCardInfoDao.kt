package com.example.testprojectshift.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BankCardInfoDao {

    @Insert
    fun insertNewValue(infoBankCard: BankCardInfoModelDB)

    @Query("Select * from history_query")
    fun selectAllHistoryQuery():List<BankCardInfoModelDB>

}