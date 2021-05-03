package com.mti.core.bottomsheet

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.mti.core.R
import kotlinx.android.synthetic.main.layout_bottom_sheet_alert.*

class BottomSheetDialogAlert : RoundedBottomSheetDialog(){
    private var type = ""
    private var message = ""
    fun setType(type: String, message: String){
        this.type = type
        this.message = message
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_bottom_sheet_alert, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when(type){
            "success" -> main_alert.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.holo_green_light))
            "warning" -> main_alert.setBackgroundColor(Color.parseColor("#FFC107"))
            "failed" -> main_alert.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.holo_red_light))

        }
        text_message.text = message
    }
}