package com.example.thindie.wantmoex.presentation.composables.util


private val cacheList = mutableListOf<String>()


fun cacheListAdd(coinName: String) {
    if (!cacheList.contains(coinName)) {
        cacheList.add(coinName)
    }
}

fun cacheListRemove(coinName: String) {
    if (cacheList.contains(coinName)) {
        cacheList.remove(coinName)
    }
}

fun cacheListCollectResult() : List<String>{
    val list = cacheList
    cacheList.clear()
    return list.toList()
}