package com.example.laoshitest.ui.collections

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.laoshitest.data.entityData.Collection
import androidx.recyclerview.widget.RecyclerView
import com.example.laoshitest.R
import com.example.laoshitest.utils.Utils

class CategoriesListAdapter(private var parents : List<Collection>) :
    RecyclerView.Adapter<CategoriesListAdapter.ViewHolder>(){

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.categories_list, parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return parents.size
    }

    override fun onBindViewHolder(holder: ViewHolder,
                                  position: Int) {
        val parent = parents[position]
        val childLayoutManager = LinearLayoutManager(
            holder.recyclerView.context, LinearLayoutManager.HORIZONTAL, false)

        holder.recyclerView.apply {
            layoutManager = childLayoutManager
            adapter = CollectionListAdapter(parent.children, context)
            setRecycledViewPool(viewPool)
        }
        holder.categoryName.text = parent.title.get(Utils.getLng(holder.recyclerView.context))
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val categoryName : TextView = itemView.findViewById(R.id.categoryName)
        val recyclerView : RecyclerView = itemView.findViewById(R.id.vertRecycler)
    }

    fun updateList(list: List<Collection>){
        parents = list
        notifyDataSetChanged()
    }
}