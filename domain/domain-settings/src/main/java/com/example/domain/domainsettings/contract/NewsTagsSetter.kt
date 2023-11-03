package com.example.domain.domainsettings.contract

interface NewsTagsSetter {

    fun setPopular()
    fun setAll()
    fun setTags(tags: Iterable<String>)
}
