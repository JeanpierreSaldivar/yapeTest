package com.saldivar.detail

/**
 * Created by César Jeanpierre Saldivar on 26/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
interface DetailRecipeNavigator {
    fun navigateOnMap(longitude:String,latitude:String)
    fun onBack()
}