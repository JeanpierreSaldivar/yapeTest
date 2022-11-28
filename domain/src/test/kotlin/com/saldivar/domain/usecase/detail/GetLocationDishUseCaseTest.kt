package com.saldivar.domain.usecase.detail

import com.saldivar.domain.common.ErrorHandler
import com.saldivar.domain.common.ResultWrapper
import com.saldivar.domain.model.CoordinatesModel
import com.saldivar.domain.repository.DetailRepository
import com.saldivar.domain.usecase.GetListRecipeUseCase
import com.saldivar.domain.usecase.GetLocationDishUseCase
import com.saldivar.domain.usecase.common.FakeErrorHandler
import com.saldivar.domain.usecase.repository.FakeDetailRepository
import kotlinx.coroutines.runBlocking
import org.junit.*

/**
 * Created by César Jeanpierre Saldivar on 27/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
class GetLocationDishUseCaseTest {
    private lateinit var useCase: GetLocationDishUseCase
    companion object{
        var model: CoordinatesModel ?= null

        @BeforeClass @JvmStatic
        fun setUpCommon(){
            model = CoordinatesModel("","")
        }

        @AfterClass
        @JvmStatic
        fun tearDownCommon(){
            model = null
        }
    }

    @Before
    fun setUp() {
        val detailRepository: DetailRepository = FakeDetailRepository()
        val errorHandler: ErrorHandler = FakeErrorHandler()
        useCase = GetLocationDishUseCase(detailRepository, errorHandler)
    }

    @Test
    fun `Given a country name When the use case is executed Then the correct coordinates are returned `(){
        runBlocking {
            model = CoordinatesModel(
                "-9.475311883171441",
                "-74.87100405856663"
            )
            val country = "Perú"
            val result = useCase(
                GetLocationDishUseCase.Params(
                    country
                )
            )
            Assert.assertTrue(result is ResultWrapper.Success<*>)
            Assert.assertEquals(model, (result as ResultWrapper.Success<CoordinatesModel>).value)
        }
    }

    @Test
    fun `Given an empty country name When the use case is executed Then a Failure is returned `(){
        runBlocking {
            val country = ""
            val result = useCase(
                GetLocationDishUseCase.Params(
                    country
                )
            )
            Assert.assertTrue(result is ResultWrapper.Failure)
        }
    }

    @Test
    fun `Dado un nombre de país Cuando se ejecuta el caso de uso Luego se devuelve con datos vacios`(){
        runBlocking {
            model = CoordinatesModel(
                "",
                ""
            )
            val country = "Perú"
            val result = useCase(
                GetLocationDishUseCase.Params(
                    country
                )
            )
            Assert.assertTrue("la latitud es vacia",
                (result as ResultWrapper.Success<CoordinatesModel>).value.latitude?.isEmpty() == true
            )
            Assert.assertTrue("la longitud es vacia",
                (result as ResultWrapper.Success<CoordinatesModel>).value.longitude?.isEmpty() == true
            )
        }
    }
}