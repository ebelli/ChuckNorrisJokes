package com.ebelli.chucknorrisjokes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ebelli.chucknorrisjokes.ui.common.ViewModelFactory
import com.ebelli.chucknorrisjokes.ui.main.MainViewModel
import com.ebelli.chucknorrisjokes.ui.utils.Status
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        viewModel = ViewModelProvider(this, ViewModelFactory())
            .get(MainViewModel::class.java)
        setupObservers()
        viewModel.getJokes(getRandomNumber())
    }


    private fun setupObservers() {
        viewModel.jokes.observe(this, Observer {
            it?.let { result ->
                when (result.status) {
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                    }
                    Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        it.message?.let { message ->
                            val snackbar = Snackbar.make(
                                root,
                                message, Snackbar.LENGTH_LONG
                            )
                            snackbar.show()
                        }
                    }
                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun getRandomNumber() = Random.nextInt(8, 21)
}