package com.bmprj.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bmprj.myapplication.model.Country

class FeedViewModel: ViewModel() {
    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()


    fun refreshData(){
        val country = Country("Türkiye","Asia","Ankara","TRY","Turkish","ww.ss.com")
        val countr1 = Country("France","Europe","Paris","TRY","Turkish","ww.ss.com")
        val countr2 = Country("Germany","Asia","Berlin","TRY","Turkish","ww.ss.com")

        val countrylist = arrayListOf<Country>(country,countr1,countr2)
        countries.value = countrylist
        countryError.value=false
        countryLoading.value = false


    }
}