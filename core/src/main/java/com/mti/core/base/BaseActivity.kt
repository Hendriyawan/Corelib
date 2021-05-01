package com.mti.core.base

import android.app.Dialog
import android.content.Intent
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.mti.core.R

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
}