package com.saldivar.home.adapter

import com.saldivar.home.HomeNavigator

/**
 * Created by César Jeanpierre Saldivar on 26/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
class OnItemClickImpl(
    private val navigator: HomeNavigator
) : OnItemClick<String> {
    override fun onItemSelectedClick(selectedSize: Int) {
        TODO("Not yet implemented")
    }

    override fun onLongPress(selectedSize: Int) {
        TODO("Not yet implemented")
    }

    override fun onItemClick(element: String) {
        navigator.navigateOnDetail(element)
    }
}