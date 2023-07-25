package com.bmprj.myapplication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

//sqlite için yerimize sınıflandırma yapar
@Entity
data class Country(
    @ColumnInfo("name")
    @SerializedName("name")
    val countyName:String?,
    @ColumnInfo("region")
    @SerializedName("region")
    val countryRegion:String?,
    @ColumnInfo("capital")
    @SerializedName("capital")
    val countryCapital:String?,
    @ColumnInfo("currency")
    @SerializedName("currency")
    val countryCurrency:String?,
    @ColumnInfo("language")
    @SerializedName("language")
    val countryLang:String?,
    @ColumnInfo("flag")
    @SerializedName("flag")
    val imageUrl:String?
    ){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0
}