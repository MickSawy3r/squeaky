package com.sixbits.assessment.feature.search.presentation

import android.content.Context
import android.content.Intent
import com.sixbits.platform.core.BaseFragment
import com.sixbits.platform.core.ContainerActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : ContainerActivity() {
    override fun fragment(): BaseFragment = SearchFragment()

    companion object {
        fun callingIntent(context: Context) = Intent(context, SearchActivity::class.java)
    }
}
