package com.bmprj.myapplication.service

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bmprj.myapplication.model.Country

@Dao
interface CountryDAO {


    @Insert
    suspend fun insertAll(vararg countries:Country):List<Long>
    //suspend -> coroutine, pause&resume
        //vararg -> farklı sayıdaki objeyi farklı sayılarla verebilmemiz için kullanıyoruz

    @Query("SELECT * FROM country")
    suspend fun getAllCountries():List<Country>

    //list primary Key döndürecek

    @Query("SELECT * FROM country WHERE uuid= :coundtyId")
    suspend fun getCountry(coundtyId:Int):Country

    @Query("DELETE FROM country")
    suspend fun delete()
}