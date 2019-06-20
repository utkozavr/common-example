package com.utkozavr.core.data.remote.provider.web

internal enum class ContentType(val type: io.ktor.http.ContentType) {
    ApplicationJson(io.ktor.http.ContentType.Application.Json),
    TextAny(io.ktor.http.ContentType.Text.Any),
    ImageAny(io.ktor.http.ContentType.Image.Any)
}