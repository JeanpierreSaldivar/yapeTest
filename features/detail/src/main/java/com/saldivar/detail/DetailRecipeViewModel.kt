package com.saldivar.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saldivar.core.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by César Jeanpierre Saldivar on 26/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
@HiltViewModel
class DetailRecipeViewModel @Inject constructor(): ViewModel() {
    private val _screenState: MutableLiveData<Event<DetailRecipeScreenState>> =
        MutableLiveData()
    val screenState: LiveData<Event<DetailRecipeScreenState>>
        get() = _screenState

    fun postEvent(event: DetailRecipeScreenEvent) {
        when (event) {
            is DetailRecipeScreenEvent.OnMapLocationButtonClicked -> {

            }
        }
    }
}