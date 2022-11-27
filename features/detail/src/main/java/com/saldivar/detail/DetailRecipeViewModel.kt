package com.saldivar.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saldivar.core.Event
import com.saldivar.domain.usecase.GetListRecipeUseCase
import com.saldivar.domain.usecase.GetLocationDishUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by César Jeanpierre Saldivar on 26/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
@HiltViewModel
class DetailRecipeViewModel @Inject constructor(
    private val getLocationDishUseCase: GetLocationDishUseCase,
): ViewModel() {
    private val _screenState: MutableLiveData<Event<DetailRecipeScreenState>> =
        MutableLiveData()
    val screenState: LiveData<Event<DetailRecipeScreenState>>
        get() = _screenState

    fun postEvent(event: DetailRecipeScreenEvent) {
        when (event) {
            is DetailRecipeScreenEvent.OnMapLocationButtonClicked -> {

            }
            is DetailRecipeScreenEvent.onDataLoad -> {

                if(event.recipe.country.isNullOrEmpty()){
                    _screenState.value = Event(DetailRecipeScreenState.hasNotLocation)
                }else{
                    _screenState.value = Event(DetailRecipeScreenState.hasLocation)
                }
                _screenState.value = Event(DetailRecipeScreenState.LoadData(event.recipe))
            }
        }
    }
}