package com.sumup.challenge.toastcatalog.di

import com.sumup.challenge.toastcatalog.data.NetworkClient
import com.sumup.challenge.toastcatalog.data.NetworkClientImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    fun bindNetworkClient(impl: NetworkClientImpl): NetworkClient
}