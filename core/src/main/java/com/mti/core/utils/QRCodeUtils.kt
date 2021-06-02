package com.mti.core.utils

import android.content.Context
import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder

/** extensions code to
 * create a string to QRCode
 */
fun String.createQrCode(context: Context, width: Int, height: Int): Bitmap {
    val multiFormatWriter = MultiFormatWriter()
    val bitmapMatrix = multiFormatWriter.encode(this, BarcodeFormat.QR_CODE, width, height)
    val barcodeEncoder = BarcodeEncoder()
    return barcodeEncoder.createBitmap(bitmapMatrix)
}