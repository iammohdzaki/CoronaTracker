package com.zaki.coronatracker.features.host

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import com.zaki.coronatracker.R
import com.zaki.coronatracker.features.base.BaseActivity
import com.zaki.coronatracker.features.discover.DiscoverFragment
import com.zaki.coronatracker.features.home.HomeFragment
import kotlinx.android.synthetic.main.bottom_navigation.*

class HostActivity : BaseActivity() , View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)
        setOnClickListeners(this,tvHome,tvDiscover)
        Handler().postDelayed({
            tvHome.performClick()
        },100)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.tvHome -> {
                handleNavigationClick(tvHome,tvDiscover)
                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.right_in,R.anim.left_out)
                    .replace(R.id.llContainer,HomeFragment())
                    .addToBackStack("HomeFragment")
                    .commit()
            }
            R.id.tvDiscover -> {
                handleNavigationClick(tvDiscover,tvHome)
                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.right_in,R.anim.left_out)
                    .replace(R.id.llContainer,DiscoverFragment())
                    .addToBackStack("DiscoverFragment")
                    .commit()
            }
        }
    }

    private fun handleNavigationClick(clicked:TextView, vararg unSelect:TextView){
        clicked.setTextColor(resources.getColor(R.color.colorAccent))
        clicked.compoundDrawables[0].setTint(resources.getColor(R.color.colorAccent))
        for(view in unSelect){
            view.setTextColor(resources.getColor(R.color.white))
            view.compoundDrawables[0].setTint(resources.getColor(R.color.white))
        }
    }
}
