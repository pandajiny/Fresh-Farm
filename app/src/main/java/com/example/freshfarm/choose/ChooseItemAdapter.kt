package com.example.freshfarm.choose

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.freshfarm.R
import kotlinx.android.synthetic.main.activity_choose_item.view.*

class ChooseItemAdapter(
    private val activity: ChooseActivity,
    private val data: ArrayList<ChooseItem>
) :
    RecyclerView.Adapter<ChooseItemAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_choose_item, parent, false) as View
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.chooseItemNameText.text = data[position].name
    }
}