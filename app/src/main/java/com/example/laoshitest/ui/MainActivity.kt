package com.example.laoshitest.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.laoshitest.R
import com.example.laoshitest.ui.books.BooksSeriesFragment
import com.example.laoshitest.ui.collections.CategoriesFragment
import com.example.laoshitest.ui.hsks.HsksFragment


class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get<MainViewModel>(MainViewModel::class.java)
        mainViewModel.initData(this)
    }

     fun onCLick(view: View){
        val trans = supportFragmentManager.beginTransaction()
        when (view.id) {
            R.id.col_id -> {
                val fragment = CategoriesFragment()
                trans.replace(R.id.root_fragment, fragment)
                trans.commit()
            }
            R.id.books_id -> {
                val fragment = BooksSeriesFragment()
                trans.replace(R.id.root_fragment, fragment)
                trans.commit()
            }
            R.id.hsk_id -> {
                val fragment = HsksFragment()
                trans.replace(R.id.root_fragment, fragment)
                trans.commit()
            }
            else -> {
            }
        }
    }
}