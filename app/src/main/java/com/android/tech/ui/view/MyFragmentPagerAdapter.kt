package com.android.tech.ui.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


/**
 * 适配器对象
 * @author 白
 */
class MyFragmentPagerAdapter : FragmentStateAdapter {
    private val fragmentList: List<Fragment>

    constructor(
        fragmentManager: FragmentManager,
        lifecycle: Lifecycle,
        fragmentList: List<Fragment>
    ) : super(fragmentManager, lifecycle) {
        this.fragmentList = fragmentList
    }

    constructor(fm: FragmentActivity?, fragmentList: List<Fragment>) : super(
        fm!!
    ) {
        this.fragmentList = fragmentList
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun getItemId(position: Int): Long {
        return fragmentList[position].hashCode().toLong()
    }
}