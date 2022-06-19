package com.nuom.technicaltask.data.repo

import com.nuom.technicaltask.data.remote.RemoteDataSource
import com.nuom.technicaltask.utils.NetworkOnly
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
    fun getRandomJoke() = NetworkOnly(
        networkCall = { remoteDataSource.getRandomJoke() }
    )

    fun getSearchedJoke(query: String) = NetworkOnly(
        networkCall = { remoteDataSource.getSearchedJoke(query) }
    )

    fun getCategories() = NetworkOnly(
        networkCall = { remoteDataSource.getCategories() }
    )

    fun getRandomCategoryJoke(category: String) = NetworkOnly(
        networkCall = { remoteDataSource.getRandomCategoryJoke(category) }
    )
}