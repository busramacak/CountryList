package com.bmprj.myapplication.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bmprj.myapplication.model.Country

@Database(entities = arrayOf(Country::class), version = 1)
abstract class CountryDatabase :RoomDatabase(){

    abstract fun countryDao() :CountryDAO

    //singleton

    companion object{

        //volatile her bir thread için sırayla nesneye erişmeyi sağlıyor.
        @Volatile private var instance :CountryDatabase?=null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context:Context) = Room.databaseBuilder(
            context.applicationContext, CountryDatabase::class.java,"countrydtabase"
        ).build()
    }
}