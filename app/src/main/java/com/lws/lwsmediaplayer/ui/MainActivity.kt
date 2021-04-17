package com.lws.lwsmediaplayer.ui

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.lws.lwsmediaplayer.data.model.ResultItunes
import com.lws.lwsmediaplayer.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), RecyclerviewListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MainAdapter

    private var items = ArrayList<ResultItunes>()

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupAdapter()
        loadData("jack")

        doSearch()
    }

    private fun showingMedia() {
        val bottom = MainBottomsheetFragment()
        bottom.show(supportFragmentManager, "MediaPlayer")

        // TODO play the sound
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
        adapter = MainAdapter(items)
        binding.recyclerview.apply {
            adapter = adapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun loadData(keyword: String) {
        viewModel.loadData(keyword,
            success = {
                items.addAll(it)
                adapter.notifyDataSetChanged()
            },
            failed = {
                showMessage(it)
            })
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onClickItem(string: String) {
        showingMedia()
    }
}