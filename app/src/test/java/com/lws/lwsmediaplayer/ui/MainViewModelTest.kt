package com.lws.lwsmediaplayer.ui

import com.lws.lwsmediaplayer.data.MainRepository
import com.lws.lwsmediaplayer.data.model.ResultItunes
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class MainViewModelTest {

    private lateinit var viewModel: MainViewModel

    private lateinit var repository: MainRepository

    private lateinit var itunes: ResultItunes

    private var slotSuccess = slot<(itunes: List<ResultItunes>) -> Unit>()
    private var slotFailed = slot<(message: String) -> Unit>()

    // @Before
    fun setUp() {
        repository = mockkClass(MainRepository::class)
        itunes = mockkClass(ResultItunes::class)

        viewModel = spyk(MainViewModel(repository))
    }

    // @Test
    fun loadData() {

//        viewModel.loadData("Jack", capture(slotSuccess), capture(slotFailed))
    }
}