package com.nuom.technicaltask.ui.main_activity

import androidx.lifecycle.ViewModel
import com.nuom.technicaltask.data.repo.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private var repository: AppRepository,
) : ViewModel() {

    fun getRandomJoke() = repository.getRandomJoke()
    fun getSearchedJoke(query:String) = repository.getSearchedJoke(query)
    fun getCategories() = repository.getCategories()
    fun getRandomCategoryJoke(category:String) = repository.getRandomCategoryJoke(category)

}