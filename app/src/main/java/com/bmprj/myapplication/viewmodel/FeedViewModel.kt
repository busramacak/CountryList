package com.bmprj.myapplication.viewmodel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bmprj.myapplication.model.Country
import com.bmprj.myapplication.service.CountryAPIService
import com.bmprj.myapplication.service.CountryDAO
import com.bmprj.myapplication.service.CountryDatabase
import com.bmprj.myapplication.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import org.jetbrains.annotations.Async.Schedule

class FeedViewModel(application: Application): BaseViewModel(application) {
    private val countryApiService = CountryAPIService()
    private val disposable = CompositeDisposable()
    private var customSharedPreferences = CustomSharedPreferences(getApplication())
    private val refreshTime = 15*60*1000*1000*1000L

    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()


    fun refreshData(){

        val updateTime = customSharedPreferences.getTime()

        if(updateTime!=null && updateTime!=0L && System.nanoTime() - updateTime < refreshTime){
            getDataFromSQLite()

        }else{
            getDataFromAPI()
        }


    }

    private fun getDataFromSQLite(){
        launch {
            val countries = CountryDatabase(getApplication()).countryDao().getAllCountries()
            showCountries(countries)
            Toast.makeText(getApplication(),"countries From SQLite",Toast.LENGTH_LONG).show()

        }
    }

    private fun getDataFromAPI(){
        countryLoading.value=true

        disposable.add(
            countryApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>(){
                    override fun onSuccess(t: List<Country>) {
                        storeInSQLite(t)
                        Toast.makeText(getApplication(),"countries From API",Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {
                        countryLoading.value=false
                        countryError.value=true
                        e.printStackTrace()
                    }

                })
        )

    }

    private fun showCountries(countryList:List<Country>){

        countries.value=countryList
        countryError.value=false
        countryLoading.value=false
    }

    private fun storeInSQLite(list:List<Country>) {
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            dao.delete()
            val listLong = dao.insertAll(*list.toTypedArray()) //listeyi tekil eleman haline getirmeyi sağlıyor

            var i = 0
            while(i<list.size){
                list[i].uuid=listLong[i].toInt()
                i++
            }

            showCountries(list)
        }

        customSharedPreferences.saveTime(System.nanoTime())
    }
}