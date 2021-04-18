package com.lws.lwsmediaplayer.ui

import android.os.Looper
import io.mockk.MockK
import io.mockk.mockkStatic
import io.mockk.spyk
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

class MainActivityTest {

    lateinit var mainActivity: MainActivity

    // @Before
    fun setUp() {

        mockkStatic(Looper::class)

        mainActivity = spyk(MainActivity())
    }

    // @Test
    fun playSound_test() {

        mainActivity.playSound("oke")
    }
}