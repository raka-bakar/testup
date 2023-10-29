package com.sumup.challenge.toastcatalog.data

import android.util.Log
import com.sumup.challenge.toastcatalog.model.Item
import java.io.IOException
import javax.inject.Inject

interface NetworkClient {
    suspend fun getItems(): List<Item>
}


class NetworkClientImpl @Inject constructor(private val api: ApiService) : NetworkClient {
    override suspend fun getItems(): List<Item> {
        // TODO: https://my-json-server.typicode.com/sumup-challenges/mobile-coding-challenge-data/items/
        try {
            val response = api.getToastList()
            return if (response.isSuccessful) {
                val listItem = response.body()?.map { item ->
                    Item(
                        id = item.id,
                        price = item.price,
                        name = item.name,
                        time = item.lastSold,
                        currency = item.currency
                    )
                }
                listItem.orEmpty()
            } else {
                emptyList()
            }
        } catch (e: IOException) {
            Log.e("IO Exception", "message : ${e.message}")
            return emptyList()
        }
    }
}
