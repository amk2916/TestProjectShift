package com.example.testprojectshift.presintaion.epoxy_model

import android.view.View
import android.view.ViewParent
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.testprojectshift.R

data class DelimiterModel (
    val idDelimiter: String//Long
):EpoxyModelWithHolder<DelimiterModel.Holder>(){
    init{
        id(idDelimiter)
    }
    class Holder:EpoxyHolder(){
        var root: View? = null
        var delimiter: View? = null
        override fun bindView(itemView: View) {
            root = itemView
            delimiter = itemView.findViewById(R.id.delimiter)
        }
    }
    override fun getDefaultLayout(): Int = R.layout.line
    override fun createNewHolder(parent: ViewParent): Holder = Holder()
}