package com.example.testprojectshift.presintaion.epoxy_model

import android.view.View
import android.view.ViewParent
import android.widget.TextView
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.testprojectshift.R
import com.example.testprojectshift.data.server.Country

data class CountryViewModel(
    private val id: String,
    private val country: Country
): EpoxyModelWithHolder<CountryViewModel.Holder>(){

    init{
        id(id)
    }

    class Holder: EpoxyHolder(){
        var root: View? = null
        var nameParamCountryView: TextView? = null
        var nameCountry: TextView? = null
        var latitude: TextView? = null
        var latitudeValue: TextView? = null
        var longitude: TextView? = null
        var longitudeValue: TextView? = null
        override fun bindView(itemView: View) {
            root = itemView
            nameParamCountryView = itemView?.findViewById(R.id.nameParamCountryView)
            nameCountry = itemView?.findViewById(R.id.nameCountry)
            latitude = itemView?.findViewById(R.id.latitude)
            latitudeValue = itemView?.findViewById(R.id.latitudeValue)
            longitude = itemView?.findViewById(R.id.longitude)
            longitudeValue = itemView?.findViewById(R.id.longitudeValue)
        }
    }

    override fun getDefaultLayout(): Int = R.layout.country_view

    override fun createNewHolder(parent: ViewParent): Holder = Holder()

    override fun bind(holder: Holder) = with(holder){
        nameCountry?.text = "${country.emoji} ${country.name}"
        latitudeValue?.text = country.latitude.toString()
        longitudeValue?.text = country.longitude.toString()

    }
}

