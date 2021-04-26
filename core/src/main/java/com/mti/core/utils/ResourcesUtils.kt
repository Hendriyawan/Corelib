package com.mti.core.utils

import android.content.Context
import com.mti.core.R
import com.mti.core.spinner.model.Country
import org.json.JSONArray

fun Context.getCountry(): List<Country> {
    val jsonFile = resources.openRawResource(R.raw.country).bufferedReader().use { it.readText() }
    return ArrayList<Country>().apply {
        JSONArray(jsonFile).apply {
            for (i in 0 until length()) {
                getJSONObject(i).apply {
                    add(
                        Country(
                            getString("name"), getString("dial_code"), getString("code")
                        )
                    )
                }
            }
        }
    }
}