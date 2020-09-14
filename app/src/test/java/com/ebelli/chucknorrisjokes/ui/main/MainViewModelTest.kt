package com.ebelli.chucknorrisjokes.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ebelli.chucknorrisjokes.data.models.Joke
import com.ebelli.chucknorrisjokes.data.models.Jokes
import com.ebelli.chucknorrisjokes.data.repositories.JokesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import com.ebelli.chucknorrisjokes.ui.utils.Result
import kotlinx.coroutines.test.*
import org.mockito.Mockito

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    private val testDispatcher = TestCoroutineDispatcher()


    @Mock
    private lateinit var jokesRepository: JokesRepository


    @Mock
    private lateinit var jokesObserver: Observer<Result<Jokes>>

    private lateinit var viewModel: MainViewModel



    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = MainViewModel(jokesRepository, testDispatcher)
    }


    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun `when getting jokes return error`() {
        runBlockingTest {
            Mockito.`when`(jokesRepository.getJokes(8))
                .thenThrow(RuntimeException())

            viewModel.jokes.observeForever(jokesObserver)
            viewModel.getJokes(8)
        }
        Mockito.verify(jokesObserver).onChanged(Result.error(null, ""))
    }

    @Test
    fun `when getting jokes return empty list throw error`() {
        runBlockingTest {
            Mockito.`when`(jokesRepository.getJokes(8))
                .thenReturn(Jokes(type = "success", value = emptyList()))

            viewModel.jokes.observeForever(jokesObserver)
            viewModel.getJokes(8)
            Mockito.verify(jokesObserver).onChanged(Result.error(null, ""))
            viewModel.jokes.removeObserver(jokesObserver)
        }
    }

    @Test
    fun `when getting jokes return random number`() {
        val jokes = listOf<Joke>(
            Joke(id=1, joke = "Joke1"),
            Joke(id=3, joke = "Joke3"),
            Joke(id=6, joke = "Joke6"),
            Joke(id=9, joke = "Joke9"),
            Joke(id=8, joke = "Joke8"),
            Joke(id=2, joke = "Joke2"),
            Joke(id=12, joke = "Joke12"),
            Joke(id=22, joke = "Joke22"),
            Joke(id=25, joke = "Joke25"),
            Joke(id=29, joke = "Joke29")
        )
        runBlockingTest {
            Mockito.`when`(jokesRepository.getJokes(10))
                .thenReturn(Jokes(type = "success", value = jokes))

            viewModel.jokes.observeForever(jokesObserver)
            viewModel.getJokes(10)
            Mockito.verify(jokesObserver).onChanged(Result.success(Jokes(type = "success", value = jokes)))
            viewModel.jokes.removeObserver(jokesObserver)
        }

    }

}