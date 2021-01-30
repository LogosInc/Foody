package com.example.foody.data

import com.example.foody.data.network.FoodRecipesApi
import com.example.foody.models.FoodRecipe
import retrofit2.Response
import retrofit2.http.QueryMap
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi
){

    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe>{
        return foodRecipesApi.getRecipes(queries)
    }

    suspend fun searchRecipes(searchQuery: Map<String, String>): Response<FoodRecipe>{
        return foodRecipesApi.searchRecipes(searchQuery)
    }
}