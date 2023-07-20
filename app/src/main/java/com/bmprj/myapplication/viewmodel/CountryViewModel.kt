package com.bmprj.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bmprj.myapplication.model.Country

class CountryViewModel : ViewModel() {

    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoom(){
        val country = Country("Türkiye","Asia","Ankara","TRY","Turkish","ww.ss.com")

        countryLiveData.value=country

    }
}