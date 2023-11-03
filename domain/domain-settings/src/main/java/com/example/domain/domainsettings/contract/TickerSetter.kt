package com.example.domain.domainsettings.contract

interface TickerSetter {
    operator fun invoke(ticker: String)
}
