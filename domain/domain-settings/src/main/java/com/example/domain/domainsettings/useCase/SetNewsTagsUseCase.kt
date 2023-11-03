package com.example.domain.domainsettings.useCase

import com.example.domain.domainsettings.contract.NewsTagsSetter
import com.example.domain.domainsettings.repository.SettingsStateApplier
import javax.inject.Inject

internal class SetNewsTagsUseCase @Inject constructor(
    private val applier: SettingsStateApplier,
) : NewsTagsSetter {

    private val allTags = defaultTags
    private val popularTags = mostPopularTags

    override fun setPopular() {
        setTags(popularTags)
    }

    override fun setAll() {
        setTags(allTags)
    }

    override fun setTags(tags: Iterable<String>) {
        applier.setTags(StateTransmitter(tagsHolder = tags.toList()))
    }
}
