package de.thinksonic.basictemplate.application_ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.android.volley.VolleyError
import org.json.JSONObject
import de.thinksonic.basictemplate.fragments.TrainerOne
import de.thinksonic.basictemplate.fragments.TrainerThree
import de.thinksonic.basictemplate.fragments.TrainerTwo
import de.thinksonic.basictemplate.app_initializer.BaseActivity
import de.thinksonic.basictemplate.R
import de.thinksonic.basictemplate.tools.RequestTypes

/*
    Created By : Shesha Vasukhi Prasad
    Date : 16-Jul-2019
    Time : 01:36
    For Project : BasicTemplate
*/

class TrainerActivity() : BaseActivity() {

    internal lateinit var one: CardView
    internal lateinit var two: CardView
    internal lateinit var three: CardView
    internal lateinit var skip: TextView
    internal lateinit var next: TextView
    private var mViewPager: ViewPager? = null
    private var mTrainerPagerAdapter: TrainerPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trainer)

    }

    override fun initViews() {

        if (!initialized) {
            initialized = true
            one = findViewById(R.id.one)
            two = findViewById(R.id.two)
            three = findViewById(R.id.three)
            skip = findViewById(R.id.skip)
            next = findViewById(R.id.next)
            mTrainerPagerAdapter = TrainerPagerAdapter(supportFragmentManager)
            mViewPager = findViewById(R.id.container)
            mViewPager!!.adapter = mTrainerPagerAdapter

            setNavigator()
            initListeners()
        }
    }

    private fun initListeners() {
        skip.setOnClickListener {
            // TODO Auto-generated method stub

            val main = Intent(this, ChooseActivity::class.java)
            startActivity(main)
            finish()
        }

        next.setOnClickListener {
            // TODO Auto-generated method stub
            if (mViewPager!!.currentItem != mTrainerPagerAdapter!!.count - 1) {
                mViewPager!!.currentItem = mViewPager!!.currentItem + 1
            } else {
                val main = Intent(this, ChooseActivity::class.java)
                startActivity(main)
                finish()
            }
            setNavigator()
        }


        mViewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageSelected(position: Int) {
                // TODO Auto-generated method stub

            }

            override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {
                // TODO Auto-generated method stub

            }

            override fun onPageScrollStateChanged(position: Int) {
                // TODO Auto-generated method stub
                setNavigator()
            }
        })

    }

    fun setNavigator() {

        if (mViewPager!!.currentItem == 0) {
            one.setCardBackgroundColor(Color.BLUE)
            two.setCardBackgroundColor(Color.GRAY)
            three.setCardBackgroundColor(Color.GRAY)
        } else if (mViewPager!!.currentItem == 1) {
            one.setCardBackgroundColor(Color.GRAY)
            two.setCardBackgroundColor(Color.BLUE)
            three.setCardBackgroundColor(Color.GRAY)
        } else {
            one.setCardBackgroundColor(Color.GRAY)
            two.setCardBackgroundColor(Color.GRAY)
            three.setCardBackgroundColor(Color.BLUE)
        }

    }

    override fun onSuccess(requestTypes: RequestTypes, jsonObject: JSONObject) {

    }

    override fun onError(requestTypes: RequestTypes, volleyError: VolleyError) {

    }

    inner class TrainerPagerAdapter internal constructor(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment? {

            when (position) {
                0 -> return TrainerOne()

                1 -> return TrainerTwo()

                2 -> return TrainerThree()

                else -> return null
            }
        }

        override fun getCount(): Int {
            return 3
        }
    }

}
