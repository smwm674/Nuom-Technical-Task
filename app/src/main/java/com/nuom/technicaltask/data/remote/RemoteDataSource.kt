package com.nuom.technicaltask.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val appService: AppService
) : BaseDataSource() {

    suspend fun getRandomJoke() = getResult { appService.getRandomJoke() }
    suspend fun getSearchedJoke(query: String) = getResult { appService.getSearchedJoke(query) }
    suspend fun getCategories() = getResult { appService.getCategories() }
    suspend fun getRandomCategoryJoke(category: String) = getResult { appService.getRandomCategoryJoke(category) }
}