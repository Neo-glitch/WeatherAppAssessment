package com.example.weatherappassessment.core.util

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.CheckResult
import androidx.fragment.app.Fragment
import com.example.weatherappassessment.R
import io.github.muddz.styleabletoast.StyleableToast
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onStart

@CheckResult
fun EditText.addFlowTextWatcher(): Flow<String> {
    return callbackFlow {
        val listener = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                trySend(s?.toString() ?: "")
            }
        }
        addTextChangedListener(listener)
        awaitClose { removeTextChangedListener(listener) }
    }.distinctUntilChanged()
}

fun View.hide(invisible: Boolean = false) {
    visibility = if (invisible) View.INVISIBLE else View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.visible(show: Boolean) {
    if (show) show() else hide()
}

//fun ImageView.setImage(drawable: Int){
//    GlideApp.with(this)
//        .load(drawable)
//        .into(this)
//}

fun Fragment.showToast(message: String, duration : Int = Toast.LENGTH_SHORT){
    StyleableToast.makeText(this.requireContext(), message, duration, R.style.ToastStyle).show()
}
