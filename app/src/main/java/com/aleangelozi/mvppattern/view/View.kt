package com.aleangelozi.mvppattern.view

interface View {

    fun setValues(countries: List<String>)

    fun onError()
}