package com.ebelli.chucknorrisjokes.data.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ebelli.chucknorrisjokes.data.models.Joke
import com.ebelli.chucknorrisjokes.data.models.Jokes
import com.ebelli.chucknorrisjokes.data.remote.ApiDataStore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class JokesRepositoryImplTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var apiDataStore: ApiDataStore

    private lateinit var jokesRepository: JokesRepository

    @Before
    fun setUp() {
        jokesRepository = JokesRepositoryImpl(apiDataStore)
    }

    @Test
    fun `get success when calling endpoint`() {
        runBlockingTest {
            Mockito.`when`(apiDataStore.getJokes(1))
                .thenReturn(Jokes(type = "success", value = emptyList()))

            val result = jokesRepository.getJokes(1)

            Assert.assertEquals(result.type, "success")
        }
    }

    @Test
    fun `get list of jokes`() {
        runBlockingTest {
            Mockito.`when`(apiDataStore.getJokes(2))
                .thenReturn(Jokes(type = "success", value = get2Jokes()))

            val result = jokesRepository.getJokes(2)

            Assert.assertEquals(result.value, get2Jokes())

        }
    }

    private fun get2Jokes() =
        listOf<Joke>(Joke(id = 1, joke = "Joke 1"), Joke(id = 2, joke = "Joke 2"))
}