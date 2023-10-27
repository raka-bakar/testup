package com.sumup.challenge.toastcatalog

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import org.junit.Assert
import org.junit.Test

class NetworkClientTests {
    private val expectedResponseData = """
[{
    "name": "Avocado Toast",
    "price": "5.99",
    "id": 1,
    "currency": "EUR",
    "last_sold": "2020-11-28T15:14:22Z"
  },
  {
    "name": "Bacon Toast",
    "id": 2,
    "price": "1.99",
    "currency": "EUR",
    "last_sold": "2021-01-30T02:24:04Z"
  },
  {
    "name": "Crunchy Toast",
    "id": 3,
    "price": "0.99",
    "currency": "EUR",
    "last_sold": "2021-03-17T03:45:47Z"
  }
]
"""

    @Test
    fun testEndpoint() = runBlocking {
        val gson = GsonBuilder().create()
        val typeToken = object : TypeToken<List<Item>>() {}.type
        val expectedResponse = gson.fromJson<List<Item>>(expectedResponseData, typeToken)

        val networkClient = NetworkClient()
        val result = networkClient.getItems()
        Assert.assertEquals(expectedResponse, result)
    }
}
