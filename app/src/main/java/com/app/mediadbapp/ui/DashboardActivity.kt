package com.app.mediadbapp.ui

import android.os.Bundle
import com.app.mediadbapp.R
import com.app.mediadbapp.baseClasses.BaseActivity
import com.app.mediadbapp.databinding.ActivityDashboardBinding
import com.app.mediadbapp.extensions.replaceFragmentIfRequiredAllowingStateLoss
import com.app.mediadbapp.ui.mediaList.MediaListFragment

class DashboardActivity : BaseActivity() {

    lateinit var binding: ActivityDashboardBinding

    private val homeFragment by lazy { MediaListFragment.newInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragmentIfRequiredAllowingStateLoss(
            fragment = homeFragment,
            frameId = R.id.content
        )
    }
}