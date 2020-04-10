package com.zaki.coronatracker.features.countries.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zaki.coronatracker.R
import com.zaki.coronatracker.model.CountryStats

/**
 * Developer : Mohammad Zaki
 * Created On : 09-04-2020
 */

class CountriesAdapter(var mActivity:Activity,var countriesList:ArrayList<CountryStats>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countriesList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    inner class CountryViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        fun bind(countryStats: CountryStats){
            itemView.apply {

            }
        }
    }

}