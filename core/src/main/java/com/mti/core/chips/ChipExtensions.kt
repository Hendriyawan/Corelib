package com.mti.core.chips

import android.util.TypedValue
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.mti.core.R

interface ChipListener {
    fun onChipSelected(item: String)
    fun onChipCloseClick(item: String)
}

fun ChipGroup.setItems(items: ArrayList<String>, listener: ChipListener) {
    removeAllViews()
    for (i in items.indices) {
        val item = items[i]
        val chip = Chip(context)
        val paddingDp = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 10F, resources.displayMetrics
        ).toInt()
        chip.apply {
            setPadding(paddingDp, paddingDp, paddingDp, paddingDp)
            text = item
            setCloseIconResource(R.drawable.ic_baseline_close_24)
            setChipIconTintResource(android.R.color.darker_gray)
            isCloseIconEnabled = true
            //action close icon
            setOnCloseIconClickListener {
                removeView(chip)
                listener.onChipCloseClick(item)
            }
        }
        addView(chip)
    }

    //action chip click
    apply {
        for (i in 0 until childCount) {
            (getChildAt(i) as Chip).let { chip ->
                listener.onChipSelected(chip.text.toString())
            }
        }
    }
}