package com.example.laoshitest.ui.hsks

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.laoshitest.R
import com.example.laoshitest.data.entityData.Hsk
import com.example.laoshitest.utils.Utils

class HskListAdapter(
    private var values: List<Hsk>,
    val context: Context,
    val clickListener: (Hsk) -> Unit
) : RecyclerView.Adapter<HskListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cathegory_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.descriptionView.text = item.title.get(Utils.getLng(context))
        holder.numberView.text = item.wordsCount.toString()
        holder.itemView.setOnClickListener{
            clickListener(item)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val descriptionView: TextView = view.findViewById(R.id.description)
        val numberView: TextView = view.findViewById(R.id.number)
    }
}