package com.zaki.coronatracker.features.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer

import com.zaki.coronatracker.R
import com.zaki.coronatracker.databinding.FragmentHomeBinding
import com.zaki.coronatracker.model.CommonResponse
import com.zaki.coronatracker.model.GlobalStats
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        bindObserver()
        return binding.root
    }

    private fun bindObserver() {
        viewModel.globalStatsData
            .observe(viewLifecycleOwner, Observer { outcome ->
                when (outcome) {
                    is CommonResponse.Success -> {
                        setData(outcome.data)
                    }
                    is CommonResponse.Failure -> {
                        makeText(activity, "Unable To Fetch Data", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            })
    }

    private fun setData(globalStats: GlobalStats){
        tvTotalCases.text=globalStats.totalCases.toString()
        tvTodayCases.text=globalStats.todayCases.toString()
        tvTotalDeaths.text=globalStats.totalDeaths.toString()
        tvTodayDeaths.text=globalStats.todayDeaths.toString()
        tvActiveCases.text=globalStats.activeCases.toString()
        tvTotalRecovered.text=globalStats.totalRecovered.toString()
    }
}
