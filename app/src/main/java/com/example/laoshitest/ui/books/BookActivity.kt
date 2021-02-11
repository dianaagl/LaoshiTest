package com.example.laoshitest.ui.books

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.laoshitest.R
import com.example.laoshitest.ui.words.WordsActivity
import com.example.laoshitest.utils.Utils
import com.squareup.picasso.Picasso

class BookActivity: AppCompatActivity() {

    companion object{
        const val bookIdString = "book_id"
    }
    private lateinit var serieViewModel: BookViewModel
    private var bookId:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.book_activity_layout)
        val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        serieViewModel = ViewModelProvider(this).get(BookViewModel::class.java)

        val title = findViewById<TextView>(R.id.title)
        val description = findViewById<TextView>(R.id.description)
        val img = findViewById<ImageView>(R.id.image)
        val recyclerView = findViewById<RecyclerView>(R.id.lessonsList)

        val bookAdapter = BookListAdapter(listOf(), this
        ) {
            bookId = it.id
            serieViewModel.getSerie(bookId)
        }
        val lessonsAdapter = LessonsAdapter(listOf(),this)
        {
            val intentAction = Intent(this, WordsActivity::class.java)
            intentAction.putExtra(WordsActivity.entityId, it.id)
            intentAction.putExtra(WordsActivity.entityType, "book")
            startActivity(intentAction)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        bookId = intent.extras?.get(bookIdString) as Int
        serieViewModel.getSerie(bookId)
        serieViewModel.serie.observe(this, {
            it?.let {
                when(it.type) {
                    "series" -> {
                        recyclerView.adapter = bookAdapter
                        bookAdapter.updateList(it.children)
                    }
                    "book" -> {
                        recyclerView.adapter = lessonsAdapter
                        lessonsAdapter.updateList(it.children)
                    }
                }
                title.text = it.title[Utils.getLng(this)]
                description.text = it.description[Utils.getLng(this)]
                Picasso.with(this)
                    .load(it.image?.thumbnail)
                    .error(R.drawable.ic_baseline_topic_24)             //optional
                    .into(img)

            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        val serie = serieViewModel.serie.value
            when(serie?.type) {
                "series" -> {
                    onBackPressed()
                }
                "book" -> {
                    bookId = serie.parentId
                    serieViewModel.getSerie(bookId)
                }
            }

        return super.onSupportNavigateUp()
    }
}