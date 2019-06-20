package com.utkozavr.core.data.remote.provider.config.web


class WebEndpoint(
    val webProtocol: WebProtocol,
    val host: String,
    val port: Int? = null,
    val user: String? = null,
    val password: String? = null
){
    override fun toString(): String {

        var credentials = ""

        if(user != null && password != null){
            credentials = "$user:$password@"
        } else if(password != null){
            credentials = "$user@"
        }

        var portString = ""

        if(port != null){
            portString = ":$port"
        }

        return "${webProtocol.protocol}$credentials$host$portString/"
    }
}