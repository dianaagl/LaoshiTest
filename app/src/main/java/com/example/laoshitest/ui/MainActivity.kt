package com.example.laoshitest.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.laoshitest.R
import com.example.laoshitest.ui.books.BooksSeriesFragment
import com.example.laoshitest.ui.collections.CategoriesFragment
import com.example.laoshitest.ui.hsks.HsksFragment


class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    var checkList: MutableList<Button> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get<MainViewModel>(MainViewModel::class.java)
        mainViewModel.initData(this)
        val button = findViewById<Button>(R.id.col_id)
        checkList.add(button)
        button.isSelected = true
    }

     fun onCLick(view: View){
        val trans = supportFragmentManager.beginTransaction()
         checkList.forEach { it.isSelected = false }
         view.isSelected = true
        when (view.id) {
            R.id.col_id -> {
                val fragment = CategoriesFragment()
                trans.replace(R.id.root_fragment, fragment)
                trans.commit()
                checkList.add(view as Button)
            }
            R.id.books_id -> {
                val fragment = BooksSeriesFragment()
                trans.replace(R.id.root_fragment, fragment)
                trans.commit()
                checkList.add(view as Button)
            }
            R.id.hsk_id -> {
                val fragment = HsksFragment()
                trans.replace(R.id.root_fragment, fragment)
                trans.commit()
                checkList.add(view as Button)
            }
            else -> {
            }
        }
    }
}