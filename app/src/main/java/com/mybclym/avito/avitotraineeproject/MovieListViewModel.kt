package com.mybclym.avito.avitotraineeproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class ItemListViewModel() : ViewModel() {

    private val itemListLiveData = MutableLiveData<List<Item>>(emptyList())
    val itemList: LiveData<List<Item>> get() = itemListLiveData

    private var count = 0
    private val list = mutableListOf<Item>()
    private val queue: Queue<Item> = LinkedList()

    fun updateItemsList() {
        Observable.interval(5000L, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                val index = Random.nextInt(0, list.size + 1)
                list.add(index, queue.poll() ?: Item(count++))
                val newList = list.toMutableList()
                Log.d("Test", "$it")
                itemListLiveData.value = newList
            }
    }

    fun deleteItem(position: Int) {
        queue.add(list[position])
        list.removeAt(position)
        val newList = list.toMutableList()
        itemListLiveData.value = newList
    }
}
