package com.mti.core.bottomsheet

import android.app.Dialog
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mti.core.R

open class RoundedBottomSheetDialog : BottomSheetDialogFragment(){
    override fun getTheme() = R.style.BottomSheetDialogTheme
    override fun onCreateDialog(savedInstanceState: Bundle?) : Dialog = BottomSheetDialog(requireContext(), theme)
}