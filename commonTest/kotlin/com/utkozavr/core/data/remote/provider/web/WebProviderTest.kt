package com.utkozavr.core.data.remote.provider.web

import com.utkozavr.core.runTest
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.SIMPLE
import io.ktor.util.InternalAPI

import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue


internal class WebProviderTest {


    @BeforeTest
    fun setUp() {
    }

    @AfterTest
    fun tearDown() {
    }

    @InternalAPI
    @Test
    fun makeCall() = runTest {




        val endpoint = "http://first.api.com/"
        val credentials: Pair<String, String>? = null

        val urlPart = "command"
        val webMethod = WebMethod.GET
        val contentType = ContentType.ApplicationJson


        val httpClientEngine = WebEngineProviderMock().getEngine()

        val webProvider = WebProvider(
            endpoint,
            Logger.SIMPLE,
            LogLevel.NONE,
            credentials,
            httpClientEngine
        )

        val r = webProvider.makeCall(urlPart, webMethod)





        assertTrue { true }
    }
}