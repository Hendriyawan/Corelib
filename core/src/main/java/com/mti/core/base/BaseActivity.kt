package com.mti.core.base

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.os.Handler
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.mti.core.R
import com.mti.core.bottomsheet.BottomSheetDialogAlert

open class BaseActivity : AppCompatActivity() {
    private lateinit var progressDialog: Dialog

    /**
     * set full screen flags
     */
    protected fun flagsFullScreen(){
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    //animation transition
    protected fun fadeAnimationTransition() {
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    /**
     * initialize progress Dialog
     */
    protected fun initProgressDialog() {
        progressDialog = Dialog(this)
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progressDialog.setContentView(R.layout.layout_progress)
        progressDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val window = progressDialog.window
        window?.setLayout(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        window?.setGravity(Gravity.CENTER)
        progressDialog.setCancelable(true)
    }

    /**
     * show progress
     */
    protected fun showProgress() {
        if (progressDialog.isShowing) return
        progressDialog.show()
    }

    /**
     * hide progress
     */
    protected fun hideProgress() {
        if (progressDialog.isShowing) progressDialog.dismiss()
    }

    /**
     * sharing the app by send
     */
    protected fun shareThisApp(title: String, message: String, packageName: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                "$message https://play.google.com/store/apps/details?=id$packageName"
            )
            type = "text/plain"
        }
        startActivity(Intent.createChooser(sendIntent, title))
    }

    /**
     * show a SnackBar
     */
    protected fun showSnackBar(view: View, type: String, message: String, listener: () -> Unit) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).apply {
            setAction(resources.getString(android.R.string.ok)) {
                listener()
                dismiss()
            }

            val backgroundColor = when (type) {
                "success" -> ContextCompat.getColor(
                    this@BaseActivity,
                    android.R.color.holo_green_light
                )
                "failed" -> ContextCompat.getColor(
                    this@BaseActivity,
                    android.R.color.holo_red_light
                )
                "warning" -> Color.parseColor("#FFC107")
                else -> android.R.color.holo_green_dark
            }
            setBackgroundTint(backgroundColor)
            setActionTextColor(ContextCompat.getColor(this@BaseActivity, android.R.color.white))
            setTextColor(ContextCompat.getColor(this@BaseActivity, android.R.color.white))
            show()
        }
    }

    /**
     * show a alert use bottom sheet
     */
    protected fun showAlertBottom(type: String, message: String){
        val bottomSheetAlert = BottomSheetDialogAlert()
        bottomSheetAlert.setType(type, message)
        bottomSheetAlert.show(supportFragmentManager, bottomSheetAlert::class.java.simpleName)
        Handler().postDelayed({
            bottomSheetAlert.dismiss()
        }, 1000)
    }
}