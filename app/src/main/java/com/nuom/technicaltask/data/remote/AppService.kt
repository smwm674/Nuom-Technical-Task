package com.nuom.technicaltask.data.remote

import com.nuom.technicaltask.data.constant.URLHelper
import com.nuom.technicaltask.data.model.Joke
import com.nuom.technicaltask.data.model.SearchJoke
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AppService {

    @GET(URLHelper.randomJoke)
    suspend fun getRandomJoke(): Response<Joke>

    @GET(URLHelper.searchJoke)
    suspend fun getSearchedJoke(@Query("query") query: String): Response<SearchJoke>

    @GET(URLHelper.categories)
    suspend fun getCategories(): Response<List<String>>

    @GET(URLHelper.randomCategoryJoke)
    suspend fun getRandomCategoryJoke(@Query("category") category: String): Response<Joke>
}