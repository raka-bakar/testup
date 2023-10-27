package com.sumup.challenge.toastcatalog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemsAdapter : RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {

    private val items = mutableListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemName = itemView.findViewById<TextView>(R.id.tv_item_name)
        private val itemPrice = itemView.findViewById<TextView>(R.id.tv_item_price)

        fun bind(item: Item) {
            itemName.text = item.name
            itemPrice.text = item.price
            setImage()
        }

        // TODO: add logic to set image (circle with item ID) for item
        private fun setImage() {
        }
    }
}
