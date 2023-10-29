package com.sumup.challenge.toastcatalog.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumup.challenge.toastcatalog.data.NetworkClient
import com.sumup.challenge.toastcatalog.model.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ItemViewModel @Inject constructor(private val client: NetworkClient):ViewModel() {
    private var _toastList: MutableLiveData<List<Item>> = MutableLiveData()
    val toastList: LiveData<List<Item>>
        get() = _toastList
    init {
        viewModelScope.launch{
            _toastList.value = client.getItems()
        }
    }
}