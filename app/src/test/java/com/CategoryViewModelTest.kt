package com

import com.example.common.Resource
import com.example.domain.model.Meals
import com.example.domain.use_cases.GetFoodsCategoryUseCase
import com.example.taskdelivery.screens.category.CategoryViewModel
import com.example.taskdelivery.screens.category.ListFoodState
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.internal.verification.VerificationModeFactory.atLeastOnce

class CategoryViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var subject: CategoryViewModel
    private val getFoodsCategoryUseCaseMock: GetFoodsCategoryUseCase = mock()

    @Before
    fun setup() {

        subject = CategoryViewModel(getFoodsCategoryUseCaseMock)
    }

    @Test
    fun getListByCategory_success() {
        val categoryMock = "beef"

        val successResponseBeef = Resource.Success<List<Meals>>(listOf())
        val loadingResponse = Resource.Loading<List<Meals>>()
        val errorResponse = Resource.Error<List<Meals>>("Error message")
        val flowBeef = flowOf(loadingResponse, successResponseBeef, errorResponse)

        `when`(getFoodsCategoryUseCaseMock.invoke(categoryMock)).thenReturn(flowBeef)

        subject.getListByCategory(categoryMock)

        verify(getFoodsCategoryUseCaseMock, atLeastOnce()).invoke(categoryMock)
        assertEquals(ListFoodState(isLoading = false), subject.listFoodByCategoryState.value)
        assertEquals(
            ListFoodState(allFoodResponse = listOf()), subject.listFoodByCategoryState.value)
        assertEquals(ListFoodState(error = ""), subject.listFoodByCategoryState.value)
    }
}