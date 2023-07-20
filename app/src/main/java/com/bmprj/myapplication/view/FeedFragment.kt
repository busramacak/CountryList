package com.bmprj.myapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bmprj.myapplication.R
import com.bmprj.myapplication.adapter.CountryAdapter
import com.bmprj.myapplication.viewmodel.FeedViewModel

class FeedFragment : Fragment() {
    private lateinit var viewModel : FeedViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())
    private lateinit var countryLoading:ProgressBar
    private lateinit var countryError:TextView
    private lateinit var recyV :RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        countryLoading = view.findViewById(R.id.countryLoading)
        countryError = view.findViewById(R.id.countryError)
        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel.refreshData()

        recyV= view.findViewById(R.id.countryList)
        recyV.layoutManager= LinearLayoutManager(context)
        recyV.adapter = countryAdapter


        observeLiveData()

    }

    private fun observeLiveData(){
        viewModel.countries.observe(viewLifecycleOwner, Observer {countries ->
        countries?.let{

            recyV.visibility=View.VISIBLE
            countryAdapter.updateCountryList(countries)
        }
        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer {error ->

            error?.let {
                if(it){
                    countryError.visibility=View.VISIBLE
                }else{
                    countryError.visibility=View.GONE
                }
            }

        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading.let {
                if(it){
                    countryLoading.visibility=View.VISIBLE
                    recyV.visibility=View.GONE
                    countryError.visibility=View.GONE
                }else{
                    countryLoading.visibility=View.GONE
                    recyV.visibility=View.VISIBLE
                    countryError.visibility=View.GONE


                }
            }
        })
    }

}