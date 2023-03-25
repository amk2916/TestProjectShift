package com.example.testprojectshift.presintaion.epoxy_model

import android.view.View
import android.view.ViewParent
import android.widget.TextView
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.testprojectshift.R

data class CardInfoModel(
    private val id: String,
    private val textContent: String,
    private val nameContent: String,
    val clickAction: (String) -> Unit = {}
):EpoxyModelWithHolder<CardInfoModel.Holder>(){

    init{
        id(id)
    }

    class Holder:EpoxyHolder(){
        var root: View? = null
        var nameParam: TextView? = null
        var valueParam: TextView? = null
        override fun bindView(itemView: View) {
            root = itemView
            nameParam = itemView.findViewById(R.id.nameParam)
            valueParam = itemView.findViewById(R.id.paramValue)
        }
    }

    override fun getDefaultLayout(): Int = R.layout.content_view

    override fun createNewHolder(parent: ViewParent): Holder = Holder()

    override fun bind(holder: Holder): Unit = with(holder){
        holder.root?.setOnClickListener {
            clickAction.invoke(id)
        }
        nameParam?.text = nameContent
        valueParam?.text = textContent

        /*valueParam?.apply {
            val color = resources.getColor(
                if (contentText != "111") R.color.purple_700 else R.color.teal_700
            )
            setBackgroundColor(color)
        }*/
    }
}
