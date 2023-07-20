package com.bmprj.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bmprj.myapplication.R
import com.bmprj.myapplication.model.Country
import com.bmprj.myapplication.view.FeedFragmentDirections

class CountryAdapter(val countryList:ArrayList<Country>):
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    class CountryViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CountryAdapter.CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_country,parent,false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryAdapter.CountryViewHolder, position: Int) {
        val name = holder.view.findViewById<TextView>(R.id.name)
        val region =holder.view.findViewById<TextView>(R.id.region)
        name.text=countryList[position].countyName
        region.text=countryList[position].countryRegion

        holder.view.setOnClickListener{
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    fun updateCountryList(newCountryList:List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

}