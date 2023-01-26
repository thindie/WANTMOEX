package com.example.thindie.wantmoex.data.network.dto

import com.example.thindie.wantmoex.data.network.dto.lastNews.NewsRawDTO
import com.google.gson.Gson

fun parseRawNewsDTOtoNewsItselfDTO(newsRawDTO: NewsRawDTO): List<NewsItselfDTO> {
    val newsList = mutableListOf<NewsItselfDTO>()
    newsRawDTO.jsonArray?.forEach {
        newsList.add(Gson().fromJson(it, NewsItselfDTO::class.java))
    }
    return newsList.toList()
}