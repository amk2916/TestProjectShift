package com.example.testprojectshift.data.db

import com.example.testprojectshift.data.localToDomainModel
import com.example.testprojectshift.data.server.BankInfoServerModel
import com.example.testprojectshift.data.toLocalModel
import com.example.testprojectshift.domain.BankCardInfo
import javax.inject.Inject

interface BankCardRepository {

    fun getAll(): List<BankCardInfo>

    fun add(infoBank: BankInfoServerModel, numberCard: String)
}

class BankCardRepositoryImpl @Inject constructor(
    private val BankCardDao: BankCardInfoDao
) : BankCardRepository {
    override fun getAll(): List<BankCardInfo> {
        val results = emptyList<BankCardInfo>().toMutableList()
        for (res in BankCardDao.selectAllHistoryQuery()) {
            results.add(res.localToDomainModel())
        }
        return results.toList()
    }
    override fun add(infoBank: BankInfoServerModel, numberCard: String) {
        BankCardDao.insertNewValue(
            infoBank.toLocalModel(numberCard)
        )

    }
}