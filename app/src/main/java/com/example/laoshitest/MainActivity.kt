package com.example.laoshitest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.Array.get

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel =  ViewModelProvider(this).get<MainViewModel>(MainViewModel::class.java)
        mainViewModel.initData(this)
    }
}