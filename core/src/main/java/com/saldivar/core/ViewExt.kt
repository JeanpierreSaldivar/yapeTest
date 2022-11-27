package com.saldivar.core

import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso

/**
 * Created by César Jeanpierre Saldivar on 25/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
fun Fragment.showSnackBar(
    message: String,
    duration: Int,
    @ColorRes color: Int
) {
    view?.let {
        val snackBar = Snackbar.make(it, message, duration)
        context?.let {
            snackBar.view.setBackgroundColor(ContextCompat.getColor(it, color))
        }
        snackBar.show()
    }
}

fun Fragment.showSnackBarError(
    message: String,
    duration: Int = Snackbar.LENGTH_LONG
) {
    showSnackBar(message, duration, com.google.android.material.R.color.design_default_color_error)
}

fun TextInputLayout?.removeErrorOnTyping() {
    this?.editText?.doOnTextChanged { _, _, _, _ -> error = null }
}

fun ImageView.loadByResourceWithoutCache(resource: String) = Picasso.get()
    .load(resource)
    .error(R.drawable.pachamanca)
    .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
    .into(this)
