package com.saldivar.home

/**
 * Created by César Jeanpierre Saldivar on 25/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
sealed class HomeScreenEvent {
    data class OnTextChanged(
        val searchNameIngredient: String,
    ) : HomeScreenEvent()

    object OnDetailButtonClicked : HomeScreenEvent()
}