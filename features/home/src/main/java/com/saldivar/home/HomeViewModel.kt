package com.saldivar.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saldivar.core.Event
import com.saldivar.core.SnackBarError
import com.saldivar.domain.common.Error
import com.saldivar.domain.common.ResultWrapper
import com.saldivar.domain.model.RecipeModel
import com.saldivar.domain.usecase.GetListRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by César Jeanpierre Saldivar on 25/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getListRecipeUseCase: GetListRecipeUseCase,
): ViewModel() {

    private var textSearch: String = ""

    private val _screenState: MutableLiveData<Event<HomeScreenState>> =
        MutableLiveData(Event(HomeScreenState.Initial))
    val screenState: LiveData<Event<HomeScreenState>>
        get() = _screenState

    private val _error: MutableLiveData<Event<SnackBarError>> = MutableLiveData()
    val error: LiveData<Event<SnackBarError>>
        get() = _error

    private val _listRecipe: MutableLiveData<Event<List<RecipeModel?>>> = MutableLiveData()
    val listRecipe: LiveData<Event<List<RecipeModel?>>>
        get() = _listRecipe

    fun postEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.OnTextChanged-> getListRecipeSearch(event.searchNameIngredient)
            is HomeScreenEvent.OnDetailButtonClicked -> detailRecipe(event.recipe)
            HomeScreenEvent.LoadListRecipeAll -> getListRecipeAll()
        }
    }

    private fun getListRecipeAll() {
        viewModelScope.launch {
            _screenState.value = Event(HomeScreenState.Loading)
            val result = getListRecipeUseCase(Unit)
            when(result){
                is ResultWrapper.Failure ->{
                    _screenState.value = Event(HomeScreenState.Initial)
                    onFailure(result.error)
                }
                is ResultWrapper.Success<List<RecipeModel?>> -> {
                    onGetListSuccess(result.value)
                }
            }
        }
    }

    private fun onGetListSuccess(value: List<RecipeModel?>) {
        _listRecipe.value = Event(value)
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

    private fun detailRecipe(recipe: RecipeModel) {

    }

    private fun getListRecipeSearch(searchNameIngredient: String) {

    }

}