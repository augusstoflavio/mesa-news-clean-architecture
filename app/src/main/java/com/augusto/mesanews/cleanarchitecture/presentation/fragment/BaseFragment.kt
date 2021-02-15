package com.augusto.mesanews.cleanarchitecture.presentation.fragment

import androidx.fragment.app.Fragment
import com.augusto.mesanews.cleanarchitecture.presentation.extensions.toast
import com.augusto.mesanews.cleanarchitecture.presentation.viewmodel.BaseViewModel

class BaseFragment: Fragment() {
    
    fun observerBaseViewModel(baseViewModel: BaseViewModel) {
        baseViewModel.error.observe(this, {
            if (it != null) {
                toast(it.message)
                baseViewModel.error.value = null
            }
        })
    }
}