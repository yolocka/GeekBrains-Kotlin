package com.example.moviestar.view

import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar


fun View.showSnackBar(message: String, length: Int, text: CharSequence, listener: View.OnClickListener) {
    Snackbar.make(this, message, length).setAction(text, listener).show()
}

fun View.showSnackBar(@StringRes message: Int, length: Int, @StringRes text: Int, listener: View.OnClickListener) {
    Snackbar.make(this, message, length).setAction(text, listener).show()
}