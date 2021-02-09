package com.example.laoshitest.ui.books

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.ConfigurationCompat
import com.example.laoshitest.R
import com.example.laoshitest.data.entityData.Book
import com.example.laoshitest.data.entityData.Collection
import com.example.laoshitest.utils.Utils
import com.squareup.picasso.Picasso

class BookListAdapter(
    private var values: List<Book>,
    val context: Context
) : RecyclerView.Adapter<BookListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.descriptionView.text = item.description[Utils.getLng(context)]
        holder.title.text = item.title[Utils.getLng(context)]
        Picasso.with(context)
            .load(item.image?.preview ?: item.image?.thumbnail)
            .error(R.drawable.ic_baseline_topic_24)             //optional
            .into(holder.imageView);
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val descriptionView: TextView = view.findViewById(R.id.book_description)
        val title: TextView = view.findViewById(R.id.book_title)
        val imageView: ImageView = view.findViewById(R.id.book_image)
    }

    fun updateList(list: List<Book>) {
        values = list
        notifyDataSetChanged()
    }
}