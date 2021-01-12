package com.kln.dailyhoroscope
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity(), BaseHoroscopePresenter.IBaseHoroscope{

    private val mPresenter :BaseHoroscopePresenter = BaseHoroscopePresenter()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPresenter.setRootView(findViewById<ConstraintLayout>(R.id.horoscope_container))
        initWelcomeView()
        initHoroscopeView()


    }

    private fun initWelcomeView() {
        val fab: FloatingActionButton = findViewById<FloatingActionButton>(R.id.fab)

        fab.apply {
            setOnClickListener {
                this.isExpanded = true
            }
        }

        findViewById<RecyclerView>(R.id.recyclerViewZodiac).apply {

            layoutManager = GridLayoutManager(context, 3)
            adapter = ZodiacRecyclerViewAdapter(object :
                ZodiacRecyclerViewAdapter.ZodiacRecyclerViewHolder.OnSignSelectedClickListener.SignSelectedInterface {

                override fun onSignSelected(sign: Zodiac) {
                    mPresenter.getHoroscope(sign, BaseHoroscopePresenter.HoroscopeDay.TODAY)
                    hideWelcomeContentShoHoroscope() //TODO: Only call once
                    fab.isExpanded = false}
            })
        }

    }

    private fun initHoroscopeView(){
        findViewById<ViewPager2>(R.id.horoscopeDayViewpager).adapter = HoroscopeDayFragmentAdapter()

        findViewById<TabLayout>(R.id.horoscopeDayTabLayout).apply {
            visibility = View.VISIBLE

            addTab(newTab(), false)
            addTab(newTab(), true)
            addTab(newTab(), false)

            getTabAt(0)?.apply {
                tag = "YESTERDAY"
                setText(R.string.Yesterday)
            }
            getTabAt(1)?.apply {
                tag = "TODAY"
                setText(R.string.Today)
            }
            getTabAt(2)?.apply {
                tag = "TOMORROW"
                setText(R.string.Tomorrow)
            }

        }

    }

    private fun hideWelcomeContentShoHoroscope() {

        this@MainActivity.apply {
            findViewById<ImageView>(R.id.welcomeBackground).visibility = View.GONE
            findViewById<ImageView>(R.id.welcomeInstructionArrow).visibility = View.GONE
            findViewById<AppCompatTextView>(R.id.welcomeTextView).visibility = View.GONE
            findViewById<AppCompatTextView>(R.id.welcomeInstructionTextView).visibility = View.GONE

           // findViewById<ConstraintLayout>(R.id.horoscope_container).visibility = View.VISIBLE
        }

    }

    override fun onResponse(horoscopeResponse: HoroscopeResponse) {
        TODO("Not yet implemented")
    }


}