package com.bbj.testpizza.view

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.res.ResourcesCompat
import androidx.viewpager2.widget.ViewPager2
import com.bbj.testpizza.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainPager = findViewById<ViewPager2>(R.id.main_pager)
        mainPager.isUserInputEnabled = false
        mainPager.adapter = MainPagerAdapter(this)

        val mainTabLayout = findViewById<TabLayout>(R.id.main_tab_layout)

        TabLayoutMediator(mainTabLayout, mainPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = resources.getString(R.string.menu)
                    tab.icon = ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.ic_baseline_fastfood_24,
                        theme
                    )
                }
                else -> {}
            }
        }

        // Choose city
        val cityChoice = findViewById<LinearLayoutCompat>(R.id.toolbar_city_choice)

        // Current city
        val currentCity = findViewById<TextView>(R.id.toolbar_current_city)

        //QR button
        val qrScan = findViewById<ImageButton>(R.id.toolbar_qr)
    }
}