package com.example.thindie.wantmoex.data.network.dto

import com.example.thindie.wantmoex.data.network.dto.lastNews.NewsRawDTO
import com.google.gson.Gson

fun parseRawNewsDTOtoNewsDTO(newsRawDTO: NewsRawDTO): List<NewsParsedDTO> {
    val newsList = mutableListOf<NewsParsedDTO>()
    newsRawDTO.jsonArray?.forEach {
        newsList.add(Gson().fromJson(it, NewsParsedDTO::class.java))
    }
    return newsList.toList()
}