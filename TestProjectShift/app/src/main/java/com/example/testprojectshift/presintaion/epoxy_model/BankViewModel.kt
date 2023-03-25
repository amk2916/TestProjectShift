package com.example.testprojectshift.presintaion.epoxy_model

import android.view.View
import android.view.ViewParent
import android.widget.TextView
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.testprojectshift.R
import com.example.testprojectshift.data.server.Bank

data class BankViewModel(
    private val id: String,
    private val bank: Bank,
    val clickAction: (String) -> Unit = {}
) : EpoxyModelWithHolder<BankViewModel.Holder>(){
    init {
        id(id)
    }
    class Holder:EpoxyHolder(){
        var root: View? = null
        var nameParamBankView: TextView? = null
        var nameBank: TextView? = null
        var urlBank: TextView? = null
        var phoneBank: TextView? = null

        override fun bindView(itemView: View) {
            root = itemView
            nameParamBankView = itemView.findViewById(R.id.nameParamBankView)
            nameBank = itemView.findViewById(R.id.nameBank)
            urlBank = itemView.findViewById(R.id.urlBank)
            phoneBank = itemView.findViewById(R.id.phoneBank)
        }
    }

    override fun getDefaultLayout(): Int = R.layout.bank_view

    override fun createNewHolder(parent: ViewParent): Holder = Holder()

    override fun bind(holder: Holder): Unit = with(holder) {
        nameBank?.text = "${bank.name}, ${bank.city}"
        urlBank?.text = bank.url
        phoneBank?.text = bank.phone
        phoneBank?.setOnClickListener {
            clickAction.invoke(id)
        }
    }
}