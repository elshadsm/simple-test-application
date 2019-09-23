package com.example.simple_test_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ClickInterface {

    private lateinit var myPagerAdapter: MyPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myPagerAdapter = MyPagerAdapter(supportFragmentManager)
        viewPager.adapter = myPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
        updateBadges()
    }

    override fun buttonClicked(index: Int) {
        myPagerAdapter.fragments[index].visibility = View.INVISIBLE
        myPagerAdapter.notifyDataSetChanged()
    }

    private fun updateBadges() {
        myPagerAdapter.visibleFragments.forEachIndexed { index, plusOneFragment ->
            tabLayout.getTabAt(index)?.let {
                if (plusOneFragment.badge > 0) {
                    val badgeDrawable = it.orCreateBadge
                    badgeDrawable.number = plusOneFragment.badge
                } else {
                    it.removeBadge()
                }
            }
        }
    }

    private inner class MyPagerAdapter(supportFragmentManager: FragmentManager) :
        FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        val fragments = listOf(
            PlusOneFragment(
                index = 0,
                title = "Fragment 1 title",
                badge = 5,
                clickInterface = this@MainActivity
            ),
            PlusOneFragment(
                index = 1,
                title = "Fragment 2 title",
                badge = 0,
                clickInterface = this@MainActivity
            ),
            PlusOneFragment(
                index = 2,
                title = "Fragment 3 title",
                badge = 16,
                clickInterface = this@MainActivity
            )
        )
        val visibleFragments: List<PlusOneFragment> get() = fragments.filter { it.visibility == View.VISIBLE }

        override fun getItem(position: Int): Fragment = visibleFragments[position]

        override fun getPageTitle(position: Int) = visibleFragments[position].title

        override fun getCount(): Int = visibleFragments.size

    }

}
