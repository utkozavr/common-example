package com.utkozavr.core.data.remote.provider.web


import io.ktor.client.HttpClient
import io.ktor.client.call.call
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.auth.Auth
import io.ktor.client.features.auth.providers.basic
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.response.readText
import io.ktor.http.content.TextContent
import io.ktor.http.isSuccess
import kotlinx.io.IOException
import kotlinx.io.charsets.Charset
import kotlinx.io.charsets.Charsets

internal class WebProvider {

    private val client:HttpClient
    private val endpoint:String

    constructor(
        endpoint: String,
        mLogger: Logger,
        logLevel: LogLevel,
        credential: Pair<String, String>?,
        engine: HttpClientEngine
    ) {

        this.endpoint = endpoint

        client = HttpClient(engine) {
            install(Logging) {
                logger = mLogger
                level = logLevel
            }

            if(credential != null){
                install(Auth) {
                    basic {
                        username = credential.first
                        password = credential.second
                    }
                }
            }
        }


    }

    suspend fun makeCall(
        urlPart: String,
        webMethod: WebMethod = WebMethod.GET,
        data: String? = null,
        contentType: ContentType? = null,
        responseCharset: Charset = Charsets.UTF_8
    ): String {

        val call = client.call("$endpoint$urlPart"){
            method = webMethod.method

            if(data != null && contentType != null){
                body = TextContent(data, contentType = contentType.type)
            }

        }

        val response = call.response


        if(response.status.isSuccess()){
            return response.readText(responseCharset)
        }

        throw IOException("Web response status: ${response.status}")


    }


}