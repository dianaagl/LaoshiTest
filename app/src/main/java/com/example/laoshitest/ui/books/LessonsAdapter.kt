package com.example.laoshitest.ui.books

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.laoshitest.R
import com.example.laoshitest.data.entityData.Book
import com.example.laoshitest.utils.Utils

class LessonsAdapter(
    private var values: List<Book>,
    val context: Context,
    val clickListener: (Book) -> Unit
) : RecyclerView.Adapter<LessonsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.lesson_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.descriptionView.text = item.title.get(Utils.getLng(context))
        holder.numberView.text = item.wordsCount.toString()
        holder.indexView.text = item.index.toString()
        holder.itemView.setOnClickListener { clickListener(item) }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val descriptionView: TextView = view.findViewById(R.id.title)
        val numberView: TextView = view.findViewById(R.id.words_number)
        val indexView: TextView = view.findViewById(R.id.index)
    }

    fun updateList(list: List<Book>) {
        values = list
    }
}