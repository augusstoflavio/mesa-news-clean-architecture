package com.augusto.mesanews.cleanarchitecture.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.augusto.mesanews.cleanarchitecture.R
import com.augusto.mesanews.cleanarchitecture.presentation.extensions.toast
import com.augusto.mesanews.cleanarchitecture.presentation.viewmodel.BaseViewModel

open class BaseFragment(@LayoutRes val layoutFragment: Int): Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutFragment, container, false)
    }
    
    fun observerBaseViewModel(baseViewModel: BaseViewModel) {
        baseViewModel.error.observe(this, {
            if (it != null) {
                toast(it.message)
                baseViewModel.error.value = null
            }
        })
    }
}