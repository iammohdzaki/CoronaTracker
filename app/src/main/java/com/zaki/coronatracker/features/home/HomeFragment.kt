package com.zaki.coronatracker.features.home

import android.os.Bundle
import android.text.format.DateUtils
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
import com.zaki.coronatracker.utils.DateTimeUtil
import com.zaki.coronatracker.utils.DateTimeUtil.TIME_FORMAT
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

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
        tvTotalCases.text=String.format("%,d",globalStats.totalCases)
        tvTodayCases.text=String.format("%,d",globalStats.todayCases)
        tvTotalDeaths.text=String.format("%,d",globalStats.totalDeaths)
        tvTodayDeaths.text=String.format("%,d",globalStats.todayDeaths)
        tvActiveCases.text=String.format("%,d",globalStats.activeCases)
        tvTotalRecovered.text=String.format("%,d",globalStats.totalRecovered)
        tvCritical.text=String.format("%,d",globalStats.criticalCases)
        tvAffectedCountries.text=globalStats.affectedCountries.toString()
        tvDeathsPer.text=globalStats.deathsPerOneMillion.toString()
        tvCasesPer.text=globalStats.casesPerOneMillion.toString()
        tvTestPer.text=globalStats.testsPerOneMillion.toString()
        tvTotalTest.text= String.format("%,d",globalStats.tests)
        //For Updated Date
        val updatedOn= Date()
        updatedOn.time=globalStats.lastUpdated
        tvCurrentDate.text = DateTimeUtil.getFormattedDate(updatedOn,TIME_FORMAT)
    }
}
