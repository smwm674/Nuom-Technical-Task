package com.nuom.technicaltask.ui.main_activity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nuom.technicaltask.R
import com.nuom.technicaltask.data.model.Joke
import com.nuom.technicaltask.databinding.FragmentSearchJokeBinding
import com.nuom.technicaltask.ui.main_activity.MainViewModel
import com.nuom.technicaltask.ui.main_activity.adaptor.JokeAdaptor
import com.nuom.technicaltask.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchJokeFragment : Fragment() {

    private lateinit var binding: FragmentSearchJokeBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchJokeBinding.inflate(inflater, container, false)

        with(binding) {
            txtSearchJoke.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    recyclerView.visibility = View.GONE

                    fetchSearchedJoke(query)
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    return false
                }
            })
        }
        return binding.root
    }

    private fun fetchSearchedJoke(query: String) {
        viewModel.getSearchedJoke(query).observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    it.data!!.result?.let { it1 ->
                        if (it1.size > 0) {
                            //show the random joke received from the api call
                            setUpRecyclerView(it1)
                        } else {
                            with(binding) {
                                //show "Empty list" message on the screen if joke received is empty from the api call
                                txtErrorMessage.setText("${it.message}+/n+${R.string.no_result}")
                                txtErrorMessage.visibility = android.view.View.VISIBLE
                                progessBar.visibility = android.view.View.GONE
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

    private fun setUpRecyclerView(list: ArrayList<Joke>) {
        val myAdapter = JokeAdaptor(list)
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        with(binding) {
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = linearLayoutManager
            recyclerView.adapter = myAdapter
            recyclerView.visibility = View.VISIBLE
            txtErrorMessage.visibility=View.GONE
        }
    }

}