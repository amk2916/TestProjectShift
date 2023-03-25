package com.example.testprojectshift.presintaion

import androidx.lifecycle.MutableLiveData
import com.airbnb.epoxy.EpoxyController
import com.example.testprojectshift.data.server.Bank
import com.example.testprojectshift.data.server.Country
import com.example.testprojectshift.domain.BankCardInfo
import java.util.Dictionary
import com.example.testprojectshift.data.server.Number
import com.example.testprojectshift.presintaion.epoxy_model.*

enum class InfoLocalOrDb {
    Local,
    Server
}

class InfoController(
    val infoLocalOrDb: InfoLocalOrDb
) : EpoxyController() {

    var infos: List<BankCardInfo> = emptyList()

    val mutableLiveData = MutableLiveData<String>()

    val actionBankPhone = MutableLiveData<String>()

    override fun buildModels() {
        infos.forEachIndexed { i, info ->
//            val values = listOf(
//                info.schemeText,
//                info.brandText,
//                info.bank
//            ).forEach {
//                it.run {
//                    when(this) {
//                        is Bank -> info.bank
//                    }
//                }
//            }


            val dictionary = mapOf<String, Any>(
                "numberCard" to info.numberCardInQuery,
                "schemeText" to info.schemeText,
                "brandText" to info.brandText,
                "number" to info.number,
                "type" to info.typeText,
                "prepaid" to info.prepaidText,
                "country" to info.country,
                "bank" to info.bank
            )
            for (dic in dictionary) {
                if (dic.key === "numberCard" && infoLocalOrDb == InfoLocalOrDb.Server) {
                    continue
                } else {
                    when (dic.key) {
                        "numberCard" -> NumberCardViewModel(
                            idNumberCardViewModel = "numberCardQuery_$i",
                            numberCardQuery = info.numberCardInQuery
                        )
                        "number" -> CardNumberViewModel(
                            id = "number_$i",
                            number = dic.value as Number
                        )
                        "bank" -> BankViewModel(
                            id = "bank_$i",
                            bank = dic.value as Bank,
                            clickAction = {actionBankPhone.value = (dic.value as Bank).phone}
                        )
                        "country" -> CountryViewModel(
                            id = "country_$i",
                            country = dic.value as Country
                        )
                        else -> CardInfoModel(
                            id = "${dic.key}_$i",
                            textContent = dic.value as String,
                            nameContent = dic.key,
                            clickAction = { mutableLiveData.value = it }
                        )
                    }.addTo(this)
                }
            }
            DelimiterModel(i.toString()).addTo(this)

        }
    }
}