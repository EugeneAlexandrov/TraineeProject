package com.mybclym.avito.avitotraineeproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val itemClickListener: OnItemClickListener?) :
    RecyclerView.Adapter<ItemViewHolder>() {

    var itemList = listOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(itemList[position])
        holder.btn.setOnClickListener {
            itemClickListener?.deleteItem(position)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setUpItemList(newItems: List<Item>) {
        itemList = newItems
    }
}

interface OnItemClickListener {
    fun deleteItem(position: Int)
}

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val id: TextView? = itemView.findViewById(R.id.id)
    val btn: Button = itemView.findViewById(R.id.btn)

    fun bind(item: Item) {
        id?.text = item.id.toString()
    }
}