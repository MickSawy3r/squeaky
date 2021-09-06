package com.sixbits.assessment.feature.search.presentation

import com.sixbits.assessment.RobolectricTest
import com.sixbits.assessment.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class SearchFragmentTest : RobolectricTest() {

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Test
    fun test() {
        launchFragmentInHiltContainer<SearchFragment> {
        }
    }
}
