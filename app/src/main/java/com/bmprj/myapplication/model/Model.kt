package com.bmprj.myapplication.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name")
    val countyName:String?,
    @SerializedName("region")
    val countryRegion:String?,
    @SerializedName("capital")
    val countryCapital:String?,
    @SerializedName("currency")
    val countryCurrency:String?,
    @SerializedName("language")
    val countryLang:String?,
    @SerializedName("flag")
    val imageUrl:String?
    )