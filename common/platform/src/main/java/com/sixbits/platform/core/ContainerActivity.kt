package com.sixbits.platform.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sixbits.platform.R
import com.sixbits.platform.databinding.ActivityLayoutBinding
import com.sixbits.platform.extensions.inTransaction

abstract class ContainerActivity : AppCompatActivity() {
    lateinit var uiBinding: ActivityLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        uiBinding = ActivityLayoutBinding.inflate(layoutInflater)
        setContentView(uiBinding.root)
        addFragment(savedInstanceState)
    }

    override fun onBackPressed() {
        (supportFragmentManager.findFragmentById(R.id.fragmentContainer) as BaseFragment).onBackPressed()
        super.onBackPressed()
    }

    private fun addFragment(savedInstanceState: Bundle?) =
        savedInstanceState ?: supportFragmentManager.inTransaction {
            add(
                uiBinding.fragmentContainer.id,
                fragment()
            )
        }

    abstract fun fragment(): BaseFragment
}
