package com.example.laoshitest.ui.words

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
import com.example.laoshitest.data.wordData.WordItem
import com.example.laoshitest.utils.Utils
import com.squareup.picasso.Picasso

class WordListAdapter(
    private var values: List<WordItem>,
    val context: Context
) : RecyclerView.Adapter<WordListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.word_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.title.text = item.word
        holder.transcription.text = item.transcriptions[0]
        holder.translation.text = item.translations[Utils.getLng(holder.level.context)]
        holder.level.text = if(item.categories.isEmpty()) "" else item.categories[0][Utils.getLng(holder.level.context)]

    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val transcription: TextView = view.findViewById(R.id.transcription)
        val translation: TextView = view.findViewById(R.id.level)
        val level: TextView = view.findViewById(R.id.translation)
    }

    fun updateList(list: List<WordItem>) {
        values = list
        notifyDataSetChanged()
    }
}