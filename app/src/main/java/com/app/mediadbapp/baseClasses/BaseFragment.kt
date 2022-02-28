package com.app.mediadbapp.baseClasses

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment(){

    open fun isTypeSame(fragment: Fragment?): Boolean {
        return fragment != null && this::class == fragment::class
    }

}