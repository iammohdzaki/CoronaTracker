package com.zaki.coronatracker.features.countries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zaki.coronatracker.R
import com.zaki.coronatracker.features.countries.CountriesFragment
import com.zaki.coronatracker.model.CountryStats
import com.zaki.coronatracker.utils.DateTimeUtil
import kotlinx.android.synthetic.main.item_view_country.view.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * Developer : Mohammad Zaki
 * Created On : 09-04-2020
 */

class CountriesAdapter(
    var mActivity: CountriesFragment,
    var countriesList:MutableList<CountryStats>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var originalList = mutableListOf<CountryStats>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_country, parent, false)
        return CountryViewHolder(view)
    }

    fun addData(list: ArrayList<CountryStats>){
        this.countriesList.addAll(list)
        originalList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return countriesList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CountryViewHolder).bind(countriesList[position])
    }

    fun search(query: String) {
        countriesList.clear()
        this.countriesList.addAll(originalList)

        if (query.isNotEmpty()) {
            var index = 0

            while (index < countriesList.size) {
                if (countriesList[index].countryName.toLowerCase().contains(query.toLowerCase()).not()) {
                    countriesList.removeAt(index)
                    index--
                }
                index++
            }

        }

        notifyDataSetChanged()
    }

    fun clear(){
        countriesList.clear()
        originalList.clear()
        notifyDataSetChanged()
    }

    inner class CountryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        fun bind(countryStats: CountryStats){
            itemView.apply {
                tvCountryName.text=countryStats.countryName
                Glide.with(mActivity)
                    .load(countryStats.countryInfo.flag)
                    .into(ivFlag)
                tvUpdatedAgo.text=countryStats.lastUpdated.toString()
                tvTotalCases.text=String.format("%,d",countryStats.totalCases)
                tvTotalRecovered.text= String.format("%,d",countryStats.totalRecovered)
                tvDeaths.text=String.format("%,d",countryStats.totalDeaths)
                val updatedOn= Date()
                updatedOn.time=countryStats.lastUpdated
                tvUpdatedAgo.text=DateTimeUtil.getRelativeTimeWithCurrentTime(updatedOn)
                tvTodayCases.text= if(countryStats.todayCases > 100) "100+" else countryStats.todayCases.toString()
            }
        }
    }

}