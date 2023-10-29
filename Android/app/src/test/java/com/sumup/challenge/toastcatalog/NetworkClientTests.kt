package com.sumup.challenge.toastcatalog

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.sumup.challenge.toastcatalog.data.ApiService
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.Types
import com.sumup.challenge.toastcatalog.data.CurrencyAdapter
import com.sumup.challenge.toastcatalog.data.ZonedDateTimeAdapter
import com.sumup.challenge.toastcatalog.model.ResponseItem
import java.time.ZonedDateTime

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
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
    private lateinit var apiService: ApiService
    private lateinit var mockWebServer: MockWebServer
    private lateinit var moshi : Moshi

    @Before
    fun setup() {
        mockWebServer = MockWebServer()

        moshi = Moshi.Builder().add(KotlinJsonAdapterFactory())
            .add(CurrencyAdapter())
            .add(ZonedDateTime::class.java, ZonedDateTimeAdapter().nullSafe())
            .build()

        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url(("")))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ApiService::class.java)

    }

    @After
    fun close() {
        mockWebServer.shutdown()
    }

    @Test
    fun `is Path Right and return correct data`() = runTest {
        mockWebServer.enqueue(MockResponse().setBody(expectedResponseData))
        val response = apiService.getToastList().body()

        val request = kotlin.runCatching {
            mockWebServer.takeRequest(
                timeout = 5,
                unit = TimeUnit.SECONDS
            )
        }.getOrNull()
        val listData = Types.newParameterizedType(List::class.java, ResponseItem::class.java)
        val adapter  = moshi.adapter<List<ResponseItem>>(listData)
        val expectedItems = adapter.fromJson(expectedResponseData)

        assertThat(request?.path).isEqualTo("/items/")
        assertThat(response).isEqualTo(expectedItems)
    }

    @Test
    fun `return empty data when response code is 500`() = runTest {
        mockWebServer.enqueue(MockResponse().setResponseCode(500)
            .setBody("{}")
        )
        val response = apiService.getToastList().body()

        assertThat(response).isEqualTo(null)
    }
}
