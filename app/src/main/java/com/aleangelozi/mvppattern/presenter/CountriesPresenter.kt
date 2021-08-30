package com.aleangelozi.mvppattern.presenter

import com.aleangelozi.mvppattern.model.CountriesService
import com.aleangelozi.mvppattern.model.Country
import com.aleangelozi.mvppattern.view.View
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.ArrayList

class CountriesPresenter(view: View) {

    private val context: View = view
    private val service: CountriesService = CountriesService()

    init {
        fetchCountries()
    }

    private fun fetchCountries() {
        service.getCountries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<Country>>() {
                override fun onSuccess(t: List<Country>?) {
                    val countryNames: MutableList<String> = ArrayList()

                    if (t != null) {
                        for (country in t) {
                            countryNames.add(country.countryName)
                        }
                        context.setValues(countryNames)
                    }
                }

                override fun onError(e: Throwable?) {
                    context.onError()
                }

            })
    }

    fun onRefresh() = fetchCountries()
}