package com.example.laoshitest.ui.books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.laoshitest.R

/**
 * A fragment representing a list of Items.
 */
class BooksFragment : Fragment() {

    lateinit var bookViewModel: BookViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.books_layout, container, false)
        val adapter = BookListAdapter(listOf(), requireContext())
        val list = view.findViewById<RecyclerView>(R.id.book_recycler)
        list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        list.adapter = adapter

        bookViewModel = ViewModelProvider(this).get<BookViewModel>(BookViewModel::class.java)
        bookViewModel.getBookSeries()
        bookViewModel.books.observe(viewLifecycleOwner, Observer {
            adapter.updateList(it)
        })
        return view
    }
}