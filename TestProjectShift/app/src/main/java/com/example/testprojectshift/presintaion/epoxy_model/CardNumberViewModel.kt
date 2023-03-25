package com.example.testprojectshift.presintaion.epoxy_model

import android.view.View
import android.view.ViewParent
import android.widget.TextView
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.testprojectshift.data.server.Number
import com.example.testprojectshift.R

data class CardNumberViewModel(
    val id: String,
    val number: Number
): EpoxyModelWithHolder<CardNumberViewModel.Holder>(){

    init {
        id(id)
    }

    class Holder:EpoxyHolder(){
        var root: View? = null
        var nameParamCardNumberView: TextView? = null
        var length: TextView? = null
        var luhn: TextView? = null
        var lengthValue: TextView? = null
        var luhnValue: TextView? = null

        override fun bindView(itemView: View) {
            root = itemView
            nameParamCardNumberView = itemView?.findViewById(R.id.nameParamCardNumberView)
            length = itemView?.findViewById(R.id.Length)
            luhn = itemView?.findViewById(R.id.Luhn)
            lengthValue = itemView?.findViewById(R.id.LengthValue)
            luhnValue = itemView?.findViewById(R.id.LuhnValue)
        }

    }

    override fun getDefaultLayout(): Int  = R.layout.card_numer_view

    override fun createNewHolder(parent: ViewParent): Holder = Holder()

    override fun bind(holder: Holder) = with(holder){
        lengthValue?.text = number.length.toString()
        luhnValue?.text = if(number.luhn?: true) "Yes" else "No"
    }
}