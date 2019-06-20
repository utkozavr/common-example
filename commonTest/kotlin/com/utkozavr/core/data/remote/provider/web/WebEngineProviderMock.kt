package com.utkozavr.core.data.remote.provider.web

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpStatusCode
import io.ktor.util.InternalAPI

internal class WebEngineProviderMock {


    @InternalAPI
    fun getEngine(): HttpClientEngine {

        return MockEngine.create {

            addHandler {

                respond("", HttpStatusCode.NotFound)

                /*
                if(it.url.host != webConfig.getEndpoint().host){
                    respond("", HttpStatusCode.NotFound)
                }

                when(it.url.host){

                    webConfig.getEndpoint().host -> {
                        respond("111")
                    }


                    else -> {
                        respond("", HttpStatusCode.NotFound)
                    }
                }
                */
            }



        }

    }

}