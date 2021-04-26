package com.mti.core.spinner

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.widget.AppCompatSpinner
import com.mti.core.spinner.model.Country
import com.mti.core.spinner.model.CountrySpinnerAdapter
import com.mti.core.utils.getCountry

class CountrySpinner : AppCompatSpinner {
    private var onCountrySelected: OnCountrySelected? = null
    interface OnCountrySelected {
        fun onSelected(country: Country)
    }

    fun setOnCountrySelected(onCountrySelected: OnCountrySelected) {
        this.onCountrySelected = onCountrySelected
    }

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    /**
     * setup
     * @param context Context
     */
    private fun init(context: Context) {
        val data = context.getCountry()
        onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                onCountrySelected?.onSelected(data[position])
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        adapter = CountrySpinnerAdapter(context, data)
    }
}