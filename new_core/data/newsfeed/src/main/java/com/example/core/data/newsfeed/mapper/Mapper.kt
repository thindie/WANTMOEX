package com.example.core.data.newsfeed.mapper

import com.example.core.domain.domainnewsfeed.entity.News
import com.example.core.network.dto.NewsDto

fun NewsDto.toNews(): News {
    return News(
        this.body.orEmpty(),
        this.categories.orEmpty(),
        this.downvotes.orEmpty(),
        this.guid.orEmpty(),
        this.id.orEmpty(),
        this.imageurl.orEmpty(),
        this.lang.orEmpty(),
        this.publishedOn ?: 0,
        this.source.orEmpty(),
        this.tags.orEmpty(),
        this.title.orEmpty(),
        this.upvotes.orEmpty(),
        this.url.orEmpty(),
    )
}
