package com.mathquiz.app.utils

import android.app.AlertDialog
import android.content.Context
import com.mathquiz.app.R

object DialogUtils {

    fun showGeneralDialog(
        context: Context,
        message: String,
        positiveText: String,
        negativeText: String,
        onClick: () -> Unit
    ) {
        val dialog = AlertDialog.Builder(context, R.style.AlertDialogCustom)
            .setTitle(context.getString(R.string.app_name))
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(positiveText) { dialog, _ ->
                onClick.invoke()
                dialog.dismiss()
            }
            .setNegativeButton(negativeText) { dialog, _ ->
                dialog.dismiss()
            }.create()

        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                .setTextColor(context.getColorFromResource(R.color.black))
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                .setTextColor(context.getColorFromResource(R.color.black))
        }
        dialog.show()
    }
}