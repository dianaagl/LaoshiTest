package com.example.laoshitest.ui.words

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.laoshitest.R
import com.example.laoshitest.ui.MainViewModel

class WordsActivity: AppCompatActivity() {

    companion object{
        const val entityId = "entityId"
        const val entityType = "entityType"
        enum class entityTypes{
            COLLECTION,
            BOOK,
            HSK
        }
    }
    lateinit var wordsViewModel: WordsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.words_mainactivity)

        val recyclerView = findViewById<RecyclerView>(R.id.wordsRecyclerView)
        val adapter = WordListAdapter(listOf(), this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        val entityId = intent.extras?.get(WordsActivity.entityId) as Int
        val entityType = intent.extras?.get(WordsActivity.entityType) as String

        wordsViewModel = ViewModelProvider(this).get<WordsViewModel>(WordsViewModel::class.java)
        wordsViewModel.initData(entityId, entityType)

        wordsViewModel.words.observe(this, Observer {
            adapter.updateList(it)
        })
    }
}