package com.example.testprojectshift.presintaion.epoxy_model

import android.view.View
import android.view.ViewParent
import android.widget.TextView
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.testprojectshift.R

data class NumberCardViewModel(
    val idNumberCardViewModel: String,
    val numberCardQuery:String
) : EpoxyModelWithHolder<NumberCardViewModel.Holder>(){
    init {
        id(idNumberCardViewModel)
    }
    class Holder: EpoxyHolder(){
        var root: View? = null
        var textName: TextView? = null
        var textValue: TextView? = null
        override fun bindView(itemView: View) {
            root = itemView
            textName = itemView.findViewById(R.id.viewName)
            textValue = itemView.findViewById(R.id.viewValue)
        }
    }

    override fun getDefaultLayout(): Int = R.layout.number_card_view
    override fun createNewHolder(parent: ViewParent): Holder = Holder()

    override fun bind(holder: Holder) = with(holder){
        textValue?.text = numberCardQuery
    }
}