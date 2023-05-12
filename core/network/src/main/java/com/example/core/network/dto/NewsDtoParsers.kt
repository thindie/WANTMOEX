package com.example.core.network.dto

import com.example.core.network.dto.lastNews.NewsRawDto
import com.google.gson.Gson

internal fun NewsRawDto.parseNewsDTO(): List<NewsDto> {
    return try {
        checkNotNull(this.jsonArray) {
            "where: 'parseNewsDTO' " +
                    "what:   from network data is null " +
                    "result: returned emptyList of News Dto "
        }.mapNotNull {
            try {
                Gson().fromJson(it, NewsDto::class.java)
            } catch (e: NullPointerException) { // todo(
                null
            }
        }
    } catch (e: IllegalStateException) {
        emptyList()
    }
}
