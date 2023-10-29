package com.sumup.challenge.toastcatalog.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sumup.challenge.toastcatalog.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemsActivity : AppCompatActivity() {

    private val viewModel: ItemViewModel by viewModels()
    private lateinit var adapter: ItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)
        initRecyclerView()
        initObserver()
    }

    private fun initObserver() {
        viewModel.toastList.observe(this) { list ->
            if (list.isNotEmpty()) {
                adapter.updateData(list.toMutableList())
            } else {
                Toast.makeText(
                    this, this.getString(R.string.general_error),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun initRecyclerView() {
        adapter = ItemsAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
}
