package com.sumup.challenge.toastcatalog.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sumup.challenge.toastcatalog.model.Item
import com.sumup.challenge.toastcatalog.R
import com.sumup.challenge.toastcatalog.Util

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
        private val itemCurrency = itemView.findViewById<TextView>(R.id.tv_item_currency)
        private val itemTime = itemView.findViewById<TextView>(R.id.tv_item_date)
        private val itemId = itemView.findViewById<TextView>(R.id.tv_item_id)

        fun bind(item: Item) {
            itemName.text = item.name
            itemPrice.text = item.price
            itemCurrency.text = item.currency
            itemTime.text = Util.dateFormatter.format(item.time)
            setImage(item.id)
        }

        // TODO: add logic to set image (circle with item ID) for item
        private fun setImage(id: Int) {
            itemId.text = id.toString()
        }
    }

    fun updateData(data: MutableList<Item>) {
        this.items.addAll(data)
        notifyDataSetChanged()
    }
}
