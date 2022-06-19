package com.nuom.technicaltask.ui.main_activity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.nuom.technicaltask.R
import com.nuom.technicaltask.databinding.FragmentJokeCategoryBinding
import com.nuom.technicaltask.ui.main_activity.MainViewModel
import com.nuom.technicaltask.ui.main_activity.adaptor.CategoryAdaptor
import com.nuom.technicaltask.utils.Resource
import com.nuom.technicaltask.utils.Utils.showRandomJoke


class JokeCategoryFragment : Fragment(), CategoryAdaptor.categoryListAction {

    private lateinit var binding: FragmentJokeCategoryBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJokeCategoryBinding.inflate(inflater, container, false)
        fetchCategories()
        return binding.root
    }

    private fun fetchCategories() {
        viewModel.getCategories().observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    it.data?.let { it1 ->
                        if (it1.size > 0) {
                            //show the joke categories received from the api call
                            setUpRecyclerView(it1 as ArrayList<String>)
                        } else {
                            with(binding) {
                                //show "Empty list" message on the screen if joke received is empty from the api call
                                txtErrorMessage.setText("${it.message}+/n+${R.string.no_result}")
                                txtErrorMessage.visibility = View.VISIBLE
                                progessBar.visibility = View.GONE
                            }
                        }
                    }
                    binding.progessBar.visibility = View.GONE
                }
                Resource.Status.ERROR -> {
                    with(binding) {
                        //show error message on screen if any error occur in the api call
                        txtErrorMessage.setText(it.message)
                        txtErrorMessage.visibility = View.VISIBLE
                        progessBar.visibility = View.GONE
                    }
                }
                Resource.Status.LOADING -> {
                    with(binding) {
                        //show progress bar while api call is in the progress
                        progessBar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun setUpRecyclerView(list: ArrayList<String>) {
        val myAdapter = CategoryAdaptor(list, this)
        val layoutManager = FlexboxLayoutManager(context)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.FLEX_END
        with(binding) {
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = myAdapter
        }
    }

    override fun onClick(data: String) {
        fetchRandomCategoryJoke(data)
    }

    private fun fetchRandomCategoryJoke(category: String) {
        viewModel.getRandomCategoryJoke(category).observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    it.data?.let { it1 ->
                        if (!it1.value.isNullOrEmpty()) {
                            //show the random joke received from the api call
                            showRandomJoke(it1, activity!!)
                        } else {
                            with(binding) {
                                //show "Empty list" message on the screen if joke received is empty from the api call
                                txtErrorMessage.setText("${it.message}+/n Empty List!")
                                txtErrorMessage.visibility = android.view.View.VISIBLE
                                progessBar.visibility = android.view.View.GONE
                                recyclerView.visibility = View.GONE
                            }
                        }
                    }
                    binding.progessBar.visibility = View.GONE
                }
                Resource.Status.ERROR -> {
                    with(binding) {
                        //show error message on screen if any error occur in the api call
                        txtErrorMessage.setText(it.message)
                        txtErrorMessage.visibility = android.view.View.VISIBLE
                        progessBar.visibility = android.view.View.GONE
                        recyclerView.visibility = View.GONE
                    }
                }
                Resource.Status.LOADING -> {
                    with(binding) {
                        //show progress bar while api call is in the progress
                        progessBar.visibility = android.view.View.VISIBLE
                    }
                }
            }
        })
    }
}