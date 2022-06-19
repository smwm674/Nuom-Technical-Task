package com.nuom.technicaltask.ui.main_activity

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.nuom.technicaltask.R
import com.nuom.technicaltask.data.model.Joke
import com.nuom.technicaltask.databinding.ActivityMainBinding
import com.nuom.technicaltask.ui.main_activity.adaptor.CategoryAdaptor
import com.nuom.technicaltask.ui.main_activity.fragment.JokeCategoryFragment
import com.nuom.technicaltask.ui.main_activity.fragment.SearchJokeFragment
import com.nuom.technicaltask.utils.Resource
import com.nuom.technicaltask.utils.Utils.showRandomJoke
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException
import java.net.URL


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var bindingMainActivity: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMainActivity = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMainActivity.root)
        context = this@MainActivity


        bindingMainActivity.randomJoke.setOnClickListener(View.OnClickListener { view ->
            fetchgetRandomJoke()
        })

        bindingMainActivity.searchJoke.setOnClickListener(View.OnClickListener { view ->
            val searchJokeFragment = SearchJokeFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.rootContainer, searchJokeFragment, searchJokeFragment.tag)
                .addToBackStack(searchJokeFragment.tag).commit()
        })
        bindingMainActivity.CategoriesJoke.setOnClickListener(View.OnClickListener { view ->
            val jokeCategoryFragment = JokeCategoryFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.rootContainer, jokeCategoryFragment, jokeCategoryFragment.tag)
                .addToBackStack(jokeCategoryFragment.tag).commit()
        })
    }

    private fun fetchgetRandomJoke() {
        viewModel.getRandomJoke().observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    it.data?.let { it1 ->
                        if (!it1.value.isNullOrEmpty()) {
                            //show the random joke received from the api call
                            showRandomJoke(it1,this)
                        } else {
                            with(bindingMainActivity) {
                                //show "Empty list" message on the screen if joke received is empty from the api call
                                txtErrorMessage.setText("${it.message}+/n Empty List!")
                                txtErrorMessage.visibility = View.VISIBLE
                                progessBar.visibility = View.GONE
                            }
                        }
                    }
                    bindingMainActivity.progessBar.visibility = View.GONE
                }
                Resource.Status.ERROR -> {
                    with(bindingMainActivity) {
                        //show error message on screen if any error occur in the api call
                        txtErrorMessage.setText(it.message)
                        txtErrorMessage.visibility = View.VISIBLE
                        progessBar.visibility = View.GONE
                    }
                }
                Resource.Status.LOADING -> {
                    with(bindingMainActivity) {
                        //show progress bar while api call is in the progress
                        progessBar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

}