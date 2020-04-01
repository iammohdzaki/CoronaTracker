package com.zaki.coronatracker.features.host

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.zaki.coronatracker.R
import com.zaki.coronatracker.features.base.BaseActivity
import kotlinx.android.synthetic.main.bottom_navigation.*
import org.w3c.dom.Text
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

class HostActivity : BaseActivity() , View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)
        setOnClickListeners(this,tvHome,tvDiscover)
    }


    override fun onClick(view: View?) {
        when(view?.id){
            R.id.tvHome -> {
                handleNavigationClick(tvHome,tvDiscover)
            }
            R.id.tvDiscover -> {
                handleNavigationClick(tvDiscover,tvHome)
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
