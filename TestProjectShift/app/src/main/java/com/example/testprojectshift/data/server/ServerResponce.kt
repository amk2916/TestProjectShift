package com.example.testprojectshift.data.server


import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

data class ServerResponce(
    val result: BankInfoServerModel
)

interface BankInfoService{
    @GET("{numberCard}")
    fun getCardInfo(@Path("numberCard") numberCard:String): Single<BankInfoServerModel>//Single<BankInfoServerModel>//Call<BankInfoServerModel>TODO
}



