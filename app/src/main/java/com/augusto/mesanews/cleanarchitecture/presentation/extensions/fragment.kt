package com.augusto.mesanews.cleanarchitecture.presentation.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.toast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}