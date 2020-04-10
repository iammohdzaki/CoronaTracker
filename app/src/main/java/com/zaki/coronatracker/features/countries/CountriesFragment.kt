package com.zaki.coronatracker.features.countries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer

import com.zaki.coronatracker.R
import com.zaki.coronatracker.databinding.FragmentCountriesBinding
import com.zaki.coronatracker.model.CommonResponse
import org.koin.androidx.viewmodel.ext.android.viewModel

class CountriesFragment : Fragment() {

    lateinit var binding:FragmentCountriesBinding
    private val viewModel:CountriesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_countries, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        bindObserver()
        return binding.root
    }

    private fun bindObserver(){
        viewModel.countriesList.observe(viewLifecycleOwner, Observer { outcome ->
            when(outcome){
                is CommonResponse.Success -> {
                    Toast.makeText(activity, outcome.data[0].countryName, Toast.LENGTH_SHORT)
                        .show()
                }
                is CommonResponse.Failure -> {
                    Toast.makeText(activity, "Unable To Fetch Data", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

}
