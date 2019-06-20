package com.utkozavr.core.data.remote.provider.web

import io.ktor.client.engine.HttpClientEngine

internal expect class WebEngineProvider(timeout: Int?) {
    fun getEngine(): HttpClientEngine
}