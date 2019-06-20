package com.utkozavr.core.data.remote.provider.config.web

import com.utkozavr.core.data.remote.datasource.config.RemoteDataSourceType
import com.utkozavr.core.domain.common.config.AppConfig
import com.utkozavr.core.domain.common.config.AppStage
import com.utkozavr.core.di.appKodein
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.SIMPLE
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.serialization.UnstableDefault
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.erased.instance

class WebConfig(val webConfigType: String, val appConfig: AppConfig) {

    var netLogLevel: NetLog = NetLog.NONE

    private val endpointMap = HashMap<AppStage, WebEndpoint>()
    private val htAccessCredentialMap = HashMap<AppStage, Pair<String, String>>()
    private val connectionTimeoutMap = HashMap<AppStage, Int>()


    companion object: KodeinAware {

        @ExperimentalCoroutinesApi
        @UseExperimental(UnstableDefault::class)
        override val kodein: Kodein
            get() = appKodein

        fun getInstance(key: RemoteDataSourceType): WebConfig {
            val c by  instance<RemoteDataSourceType, WebConfig>(arg = key)
            return c
        }
    }

    fun setEndpoint(appStage: AppStage, endpoint: WebEndpoint){
        endpointMap[appStage] = endpoint
    }

    fun setHtAccessCredential(appStage: AppStage, credential: Pair<String, String>){
        htAccessCredentialMap[appStage] = credential
    }

    internal fun getEndpoint(): WebEndpoint {

        val endpoint = endpointMap[appConfig.appStage]

        if(endpoint == null){
            throw Exception("Http endpoint not configured for appStage: ${appConfig.appStage}")
        }

        return endpoint
    }

    internal fun getHtAccessCredential(): Pair<String, String>?{
        return htAccessCredentialMap[appConfig.appStage]
    }

    internal fun getConnectionTimeout(): Int? {
        return connectionTimeoutMap[appConfig.appStage]
    }

    internal fun getLogLevel(): LogLevel {

        when(netLogLevel){
            NetLog.NONE -> return LogLevel.NONE
            NetLog.INFO -> return LogLevel.INFO
            NetLog.BODY -> return LogLevel.BODY
            NetLog.ALL -> return LogLevel.ALL
        }

    }

    internal fun getLogger(): Logger {
        return Logger.SIMPLE
    }


    enum class NetLog {
        NONE, INFO, BODY, ALL
    }
}