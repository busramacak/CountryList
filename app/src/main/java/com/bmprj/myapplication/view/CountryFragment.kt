package com.bmprj.myapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bmprj.myapplication.R
import com.bmprj.myapplication.viewmodel.CountryViewModel

class CountryFragment : Fragment() {
    private lateinit var viewModel : CountryViewModel
    private lateinit var countryName : TextView
    private lateinit var countryCapital : TextView
    private lateinit var countryRegion : TextView
    private lateinit var countryCurrency : TextView
    private lateinit var countryLang : TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        countryName = view.findViewById(R.id.countryName)
        countryCapital = view.findViewById(R.id.countryCapital)
        countryRegion = view.findViewById(R.id.countryRegion)
        countryCurrency= view.findViewById(R.id.countryCurrency)
        countryLang = view.findViewById(R.id.countryLang)


        viewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)

        viewModel.getDataFromRoom()
        observeLiveData()


    }

    private fun observeLiveData(){
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->
            country?.let {
                countryName.text = country.countyName
                countryCapital.text = country.countryCapital
                countryCurrency.text = country.countryCurrency
                countryRegion.text = country.countryRegion
                countryLang.text = country.countryLang
            }
        })
    }
}