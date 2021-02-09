package com.example.laoshitest.ui.collections

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
class CategoriesFragment : Fragment() {

    lateinit var collectionViewModel: CollectionViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.categories_list, container, false)
        val adapter = CategoriesListAdapter(listOf())
        val list = view.findViewById<RecyclerView>(R.id.vertRecycler)
        list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        list.adapter = adapter

        collectionViewModel = ViewModelProvider(this).get<CollectionViewModel>(CollectionViewModel::class.java)
        collectionViewModel.getAllCollections()
        collectionViewModel.collections.observe(viewLifecycleOwner, Observer {
            adapter.updateList(it)
        })
        return view
    }
}