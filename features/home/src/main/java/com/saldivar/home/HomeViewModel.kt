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
import com.saldivar.home.adapter.RecipeModelUI
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
    private val viewMapper: ViewMapper
): ViewModel() {
    private var listRecipeShow: List<RecipeModelUI?> = listOf()
    private val _screenState: MutableLiveData<Event<HomeScreenState>> =
        MutableLiveData()
    val screenState: LiveData<Event<HomeScreenState>>
        get() = _screenState

    private val _error: MutableLiveData<Event<SnackBarError>> = MutableLiveData()
    val error: LiveData<Event<SnackBarError>>
        get() = _error

    fun postEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.OnTextChanged-> getListRecipeSearch(event.searchNameIngredient)
            HomeScreenEvent.LoadListRecipeAll -> getListRecipeAll()
        }
    }

    private fun getListRecipeAll() {
        viewModelScope.launch {
            _screenState.value = Event(HomeScreenState.Loading)
            val result = getListRecipeUseCase(Unit)
            when(result){
                is ResultWrapper.Failure ->{
                    onFailure(result.error)
                }
                is ResultWrapper.Success<List<RecipeModel?>> -> {
                    onGetListSuccess(result.value)
                }
            }
        }
    }

    private fun onGetListSuccess(value: List<RecipeModel?>) {
        listRecipeShow = viewMapper.fromModelToView(value)
        validateListEmpty(listRecipeShow)
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

    private fun getListRecipeSearch(searchNameIngredient: String) {
        if (searchNameIngredient.isEmpty()){
            getListRecipeAll()
        }else{
            _screenState.value = Event(HomeScreenState.Loading)
            val list = searchTextInListRecipe(searchNameIngredient)
            validateListEmpty(list)
        }
    }

    private fun searchTextInListRecipe(searchNameIngredient: String) = listRecipeShow.filter { model-> model?.name?.contains(searchNameIngredient) == true || model?.listIngredients?.firstOrNull{ it?.ingredient?.contains(searchNameIngredient) == true }!= null}

    private fun validateListEmpty(value: List<RecipeModelUI?>){
        if (value.isNotEmpty()){
            _screenState.value = Event(HomeScreenState.DataLoaded(value))
        }else{
            _screenState.value = Event(HomeScreenState.EmptyState)
        }
    }
}