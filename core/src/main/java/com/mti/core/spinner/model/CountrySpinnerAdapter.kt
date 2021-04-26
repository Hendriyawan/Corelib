package com.mti.core.spinner.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.mti.core.R
import java.util.*

class CountrySpinnerAdapter(context: Context, private val countries: List<Country>) : ArrayAdapter<Country>(context, 0, countries){
    private val layoutInflater = LayoutInflater.from(context)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: layoutInflater.inflate(R.layout.item_spinner_country_code_number, parent, false)
        getItem(position)?.let { country ->
            setItemCountry(view, country)
        }
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = layoutInflater.inflate(R.layout.item_spinner_country_code_number, parent, false)
        getItem(position)?.let { country ->
            setItemCountry(view, country)
        }
        return view
    }

    override fun getCount() = countries.size
    private fun setItemCountry(view: View, country: Country) {
        val dialCode = view.findViewById<AppCompatTextView>(R.id.item_spinner_code)
        val icon = view.findViewById<AppCompatImageView>(R.id.item_spinner_icon)
        dialCode.apply {
            text = country.dial_code
            isSelected = true
        }
        val resName = "flag_${country.code}".toLowerCase(Locale.ROOT)
        val resourceId = context.resources.getIdentifier(resName, "drawable", context.packageName)
        icon.setImageResource(resourceId)
    }
}