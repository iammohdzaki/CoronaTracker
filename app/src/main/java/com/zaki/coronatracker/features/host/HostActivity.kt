package com.zaki.coronatracker.features.host

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.zaki.coronatracker.R
import com.zaki.coronatracker.features.base.BaseActivity
import kotlinx.android.synthetic.main.activity_host.*


class HostActivity : BaseActivity() , View.OnClickListener{

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)

        navController=Navigation.findNavController(this,R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(nvBottom,navController)
    }

    override fun onClick(view: View?) {
        when(view?.id){
        }
    }

}
