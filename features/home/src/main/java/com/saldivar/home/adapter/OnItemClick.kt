package com.saldivar.home.adapter

/**
 * Created by César Jeanpierre Saldivar on 26/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
interface OnItemClick<M>  {
    fun onItemSelectedClick(selectedSize: Int)
    fun onLongPress(selectedSize: Int)
    fun onItemClick(element: M)
}