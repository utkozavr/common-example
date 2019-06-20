package com.utkozavr.core.data.remote.provider.web

internal enum class WebMethod(val method: io.ktor.http.HttpMethod){
    GET(io.ktor.http.HttpMethod.Get),
    POST(io.ktor.http.HttpMethod.Post)
}