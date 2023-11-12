package com.example.domain

import com.example.common.Resource
import com.example.domain.model.Meals
import com.example.domain.repository.FoodByCategoryRepo
import com.example.domain.use_cases.GetFoodsCategoryUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.Mockito.`when`

class GetFoodsCategoryUseCaseTest {

    private lateinit var subject: GetFoodsCategoryUseCase
    private val repositoryMock: FoodByCategoryRepo = mock()

    @Before
    fun setup() {
        subject = GetFoodsCategoryUseCase(repositoryMock)
    }

    @Test
    fun getFoodsCategoryUseCase_success() = runBlocking {
        val categoryMock = "beef"
        val mockMealsList: List<Meals> = mock()
        val successResponse: Resource<List<Meals>> = Resource.Success(mockMealsList)

        `when`(repositoryMock.getFoodsByCategory(categoryMock)).thenReturn(flow {
            emit(successResponse)
        })
        val result = subject.invoke(categoryMock)
        result.collect { resource ->
            assertEquals(successResponse, resource)
        }
        verify(repositoryMock).getFoodsByCategory(categoryMock)
        verifyNoMoreInteractions(repositoryMock)
    }

    @Test
    fun getFoodsCategoryUseCase_error() = runBlocking {
        val categoryMock = "beef"
        val errorMessage = "Oops, something went wrong!"
        val errorResponse: Resource<List<Meals>> = Resource.Error(message = errorMessage)

        `when`(repositoryMock.getFoodsByCategory(categoryMock)).thenReturn(flow {
            try {
                throw RuntimeException("Test exception")
            } catch (e: Exception) {
                emit(errorResponse)
            }
        })

        val result = subject.invoke(categoryMock)
        result.collect { resource ->
            assertEquals(errorResponse, resource)
        }
        verify(repositoryMock).getFoodsByCategory(categoryMock)
        verifyNoMoreInteractions(repositoryMock)
    }
}