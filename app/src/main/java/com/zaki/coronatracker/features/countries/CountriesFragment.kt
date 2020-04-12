package com.zaki.coronatracker.features.countries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.zaki.coronatracker.R
import com.zaki.coronatracker.databinding.FragmentCountriesBinding
import com.zaki.coronatracker.features.countries.adapter.CountriesAdapter
import com.zaki.coronatracker.model.CommonResponse
import com.zaki.coronatracker.model.CountryStats
import kotlinx.android.synthetic.main.fragment_countries.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CountriesFragment : Fragment() {

    private lateinit var binding:FragmentCountriesBinding
    private val viewModel:CountriesViewModel by viewModel()
    private val listAdapter by lazy { CountriesAdapter(this, mutableListOf()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_countries, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        bindObserver()
        setListeners()
        setUpRecyclerView()
        return binding.root
    }

    private fun bindObserver(){
        viewModel.countriesList.observe(viewLifecycleOwner, Observer { outcome ->
            when(outcome){
                is CommonResponse.Success -> {
                    listAdapter.addData(outcome.data)
                }
                is CommonResponse.Failure -> {
                    Toast.makeText(activity, "Unable To Fetch Data", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

    private fun setListeners(){
        binding.tvSearchCountry.doAfterTextChanged { text ->
            listAdapter.search(text.toString())
        }
    }

    private fun setUpRecyclerView(){
        binding.rvCountries.apply {
            layoutManager=LinearLayoutManager(context)
            adapter=listAdapter
        }
    }

}
