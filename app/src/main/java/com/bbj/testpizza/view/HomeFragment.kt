package com.bbj.testpizza.view

import androidx.fragment.app.Fragment
import com.bbj.testpizza.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {

    override val layoutId: Int = R.layout.fragment_home

    val menuViewModel by viewModel<HomeViewModel>()



}