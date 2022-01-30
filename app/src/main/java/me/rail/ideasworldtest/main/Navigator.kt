package me.rail.ideasworldtest.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import me.rail.ideasworldtest.R
import me.rail.ideasworldtest.screens.photos.item.ItemFragment
import javax.inject.Inject

class Navigator @Inject constructor(activity: FragmentActivity) {
    private val fragmentManager = activity.supportFragmentManager

    fun replaceFragment(fragment: Fragment) {
        val transaction = fragmentManager.beginTransaction()

        if (fragment is ItemFragment) {
            transaction
                .addToBackStack(fragment::class.java.name)
                .add(R.id.container, fragment)
        } else {
            transaction.replace(R.id.container, fragment)
        }

        transaction.commit()
    }

    fun addFragment(fragment: Fragment) {
        fragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}