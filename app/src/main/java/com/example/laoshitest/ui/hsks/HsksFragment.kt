package com.example.laoshitest.ui.hsks

import android.content.Intent
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
import com.example.laoshitest.data.entityData.Hsk
import com.example.laoshitest.ui.words.WordsActivity

/**
 * A fragment representing a list of Items.
 */
class HsksFragment : Fragment() {

    lateinit var hsksViewModel: HsksViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.categories_list, container, false)
        val adapter = HskLevelListAdapter(listOf()
        ) { item: Hsk ->
            val intent = Intent(context, WordsActivity::class.java)
            intent.putExtra(WordsActivity.entityType, "hsk");
            intent.putExtra(WordsActivity.entityId, item.id);
            startActivity(intent)
        }
        val list = view.findViewById<RecyclerView>(R.id.vertRecycler)
        list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        list.adapter = adapter

        hsksViewModel = ViewModelProvider(this).get<HsksViewModel>(HsksViewModel::class.java)
        hsksViewModel.getAllCollections()
        hsksViewModel.collections.observe(viewLifecycleOwner, Observer {
            adapter.updateList(it)
        })
        return view
    }
}