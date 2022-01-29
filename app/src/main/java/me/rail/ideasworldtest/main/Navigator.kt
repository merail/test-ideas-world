package me.rail.ideasworldtest.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import me.rail.ideasworldtest.R
import javax.inject.Inject

class Navigator @Inject constructor(activity: FragmentActivity) {
    private val fragmentManager = activity.supportFragmentManager

    fun replaceFragment(fragment: Fragment) {
        fragmentManager.beginTransaction().replace(R.id.container, fragment)
            .commit()
    }

    fun addFragment(fragment: Fragment) {
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}