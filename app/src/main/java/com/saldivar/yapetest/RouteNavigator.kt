package com.saldivar.yapetest

import androidx.navigation.NavController
import com.saldivar.detail.DetailRecipeNavigator
import com.saldivar.home.HomeFragment
import com.saldivar.home.HomeFragmentDirections
import com.saldivar.home.HomeNavigator
import com.saldivar.home.adapter.RecipeModelUI
import java.lang.ref.WeakReference

/**
 * Created by César Jeanpierre Saldivar on 26/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
object RouteNavigator: HomeNavigator,DetailRecipeNavigator {
    private var navController: WeakReference<NavController>? = null

    fun bind(nav: NavController) {
        this.navController = WeakReference<NavController>(nav)
    }

    fun unbind() {
        this.navController = null
    }

    override fun navigateOnDetail(recipe: String) {
        navController?.get()
            ?.navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailRecipeFragment(recipe)
            )
    }

    override fun navigateOnMap(longitude: String, latitude: String) {
        //TODO("Not yet implemented")
    }

    override fun onBack() {
        navController?.get()?.popBackStack()
    }

}