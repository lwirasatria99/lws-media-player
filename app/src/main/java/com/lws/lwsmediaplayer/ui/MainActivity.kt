package com.lws.lwsmediaplayer.ui

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.lws.lwsmediaplayer.R
import com.lws.lwsmediaplayer.data.model.ResultItunes
import com.lws.lwsmediaplayer.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottomsheet_media.view.*
import okhttp3.internal.notify
import java.io.IOException

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var mainAdapter: MainAdapter

    private var itemPosition = -1
    private var item = ResultItunes()
    private var items = ArrayList<ResultItunes>()

    private val viewModel: MainViewModel by viewModels()

    private var mediaPlayer: MediaPlayer = MediaPlayer()

    var isPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupAdapter()
        loadData("jack")
        doSearch()
    }

    private fun showingMedia(url: String) {
        binding.containerMedia.visibility = View.VISIBLE
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        stopSound()
        playSound(url)

        binding.containerMedia.btnPlayOrPause.apply {
            setOnClickListener {
                if (!isPlaying) {
                    playSound(url)
                } else {
                    stopSound()
                }
            }
        }
    }

    private fun stopSound() {
        isPlaying = false
        binding.containerMedia.btnPlayOrPause.setImageResource(R.drawable.ic_play_24)
        mediaPlayer.stop()
        mediaPlayer.reset()
    }

    fun playSound(url: String) {
        isPlaying = true
        binding.containerMedia.btnPlayOrPause.setImageResource(R.drawable.ic_pause_24)

        try {
            mediaPlayer.setDataSource(url)
            mediaPlayer.prepare()
            mediaPlayer.start()
            binding.containerMedia.tvTrackPlaying.text = item.trackName
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun doSearch() {
        etSearch.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
                if (p1 == EditorInfo.IME_ACTION_DONE) {
                    val keyword = etSearch.text.toString()
                    loadData(keyword)
                    return true
                }

                return false
            }
        })
    }

    private fun setupAdapter() {
        mainAdapter = MainAdapter(items) { itunes: ResultItunes, position: Int ->
            item = itunes
            itemPosition = position
            showingMedia(itunes.previewUrl)
        }

        binding.recyclerview.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun loadData(keyword: String) {
        viewModel.loadData(keyword,
            success = {
                items.clear()
                items.addAll(it)
                mainAdapter.notifyDataSetChanged()
            },
            failed = {
                showMessage(it)
            })
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}