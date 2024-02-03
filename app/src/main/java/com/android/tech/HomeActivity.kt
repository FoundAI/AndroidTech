package com.android.tech


import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.android.tech.databinding.ActivityHomeBinding
import com.android.tech.databinding.HomeTabBinding
import com.android.tech.ui.view.FragmentHomePage
import com.android.tech.ui.view.MyFragmentPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.math.log
import kotlin.properties.Delegates


class HomeActivity : AppCompatActivity() {

    private val TAG: String = "HomeActivity"

    private val binding by lazy {
        ActivityHomeBinding.inflate(
            layoutInflater
        )
    }

    private var COLOR_MAIN by Delegates.notNull<Int>()
    private var COLOR_BLACK by Delegates.notNull<Int>()

    private val tabTitles = listOf("Home", "Setting")
    private val tabIcons = listOf(R.mipmap.home, R.mipmap.setting)
    private val tabSelectedIcons = listOf(R.mipmap.home_s, R.mipmap.setting_s)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        addMyFragmentPagerAdapter()
    }


    /**
     * TabLayout关联ViewPager2
     */
    private fun addMyFragmentPagerAdapter() {
        COLOR_MAIN = getColor(R.color.main)
        COLOR_BLACK = getColor(R.color.black)
        val fragmentList: MutableList<Fragment> = ArrayList<Fragment>()

        val homePage: FragmentHomePage = FragmentHomePage()
        val community: FragmentHomePage = FragmentHomePage()

        fragmentList.add(homePage)
        fragmentList.add(community)

        val fragmentAdapter: MyFragmentPagerAdapter =
            MyFragmentPagerAdapter(supportFragmentManager, lifecycle, fragmentList)
        //控制用户是否可以通过手势滑动切换页面
        binding.viewPager.isUserInputEnabled = false
        binding.viewPager.adapter = fragmentAdapter

        TabLayoutMediator(
            binding.navigation, binding.viewPager
        ) { tab: TabLayout.Tab, position: Int ->

            val customTabView = HomeTabBinding.inflate(layoutInflater)
            customTabView.tabText.text = tabTitles[position]
            when (position) {
                0 -> {
                    customTabView.tabText.setTextColor(COLOR_MAIN)
                    customTabView.tabIcon.setBackgroundDrawable(getDrawable(tabSelectedIcons[position]))
                }

                else -> {
                    customTabView.tabText.setTextColor(COLOR_BLACK)
                    customTabView.tabIcon.setBackgroundDrawable(getDrawable(tabIcons[position]))
                }
            }
            tab.customView = customTabView.root
        }.attach()

        binding.navigation.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val position = tab.position
                tab.customView?.let {
                    val title = it.findViewById<TextView>(R.id.tab_text)
                    val icon = it.findViewById<ImageView>(R.id.tab_icon)
                    title.setTextColor(COLOR_MAIN)
                    icon.setBackgroundDrawable(getDrawable(tabSelectedIcons[position]))
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                val position = tab.position
                tab.customView?.let {
                    val title = it.findViewById<TextView>(R.id.tab_text)
                    val icon = it.findViewById<ImageView>(R.id.tab_icon)
                    title.setTextColor(COLOR_BLACK)
                    icon.setBackgroundDrawable(getDrawable(tabIcons[position]))
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

        // 监听页面切换事件
        binding.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                // 更新TabLayout的选中状态
                binding.navigation.selectTab(binding.navigation.getTabAt(position))
            }
        })

    }


}