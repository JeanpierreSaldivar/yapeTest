package com.saldivar.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saldivar.core.Event
import com.saldivar.core.SnackBarError
import com.saldivar.domain.common.Error
import com.saldivar.domain.common.ResultWrapper
import com.saldivar.domain.model.CoordinatesModel
import com.saldivar.domain.usecase.GetLocationDishUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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

    private val _error: MutableLiveData<Event<SnackBarError>> = MutableLiveData()
    val error: LiveData<Event<SnackBarError>>
        get() = _error

    fun postEvent(event: DetailRecipeScreenEvent) {
        when (event) {
            is DetailRecipeScreenEvent.OnMapLocationButtonClicked -> {
                getLocationMap(event.country)
            }
            is DetailRecipeScreenEvent.onDataLoad -> {
                _screenState.value =if(event.recipe.country.isNullOrEmpty()){
                     Event(DetailRecipeScreenState.hasNotLocation)
                }else{
                    Event(DetailRecipeScreenState.hasLocation)
                }
                _screenState.value = Event(DetailRecipeScreenState.LoadData(event.recipe))
            }
        }
    }

    private fun getLocationMap(country: String) {
        viewModelScope.launch {
            _screenState.value = Event(DetailRecipeScreenState.Loading)
            when(val result = getLocationDishUseCase(GetLocationDishUseCase.Params(country))){
                is ResultWrapper.Failure -> onFailure(result.error)
                is ResultWrapper.Success -> onGetLocationDishSuccess(result.value,country)
            }
        }
    }

    private fun onGetLocationDishSuccess(location : CoordinatesModel,country: String) {
        if(location.latitude.isNullOrEmpty()|| location.longitude.isNullOrEmpty()){
            showGenericError("No hay coordenadas")
        }else{
            _screenState.value = Event(
                DetailRecipeScreenState.LoadLocation(
                    location.latitude!!,
                    location.longitude!!,
                    country
                )
            )
        }

    }

    private fun onFailure(error: Error) {
        when (error) {
            is Error.GenericError -> showGenericError(error.message)
            Error.NetworkError -> showGenericError("Check internet connexion")
        }
    }

    private fun showGenericError(error: String) {
        _error.value =
            Event(SnackBarError(error))
    }
}