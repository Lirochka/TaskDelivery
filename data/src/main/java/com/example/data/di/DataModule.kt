package com.example.data.di

import android.content.Context
import com.example.common.Constant.BASE_URL
import com.example.data.network.ApiService
import com.example.data.repository.FoodByCategoryRepoImpl
import com.example.data.room.FoodDao
import com.example.data.room.FoodDataBase
import com.example.domain.repository.FoodByCategoryRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideCategoryRepository(api: ApiService, foodDao: FoodDao): FoodByCategoryRepo {
        return FoodByCategoryRepoImpl(api, foodDao)
    }

    @Provides
    fun provideDataBase(@ApplicationContext context: Context): FoodDataBase {
        return FoodDataBase.getDatabase(context)
    }
    @Provides
    fun provideDAO(foodDataBase: FoodDataBase): FoodDao {
        return foodDataBase.getSavedFoodDao()
    }
}