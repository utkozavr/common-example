package com.utkozavr.core.di.data.module

import com.utkozavr.core.data.remote.provider.config.web.WebConfig
import com.utkozavr.core.data.remote.datasource.config.RemoteDataSourceType
import com.utkozavr.core.data.remote.provider.web.WebEngineProvider
import com.utkozavr.core.data.remote.provider.web.WebProvider
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.multiton

val remoteProviderModule = Kodein.Module("remoteProviderModule"){

    bind<WebEngineProvider>() with  multiton {
            key: RemoteDataSourceType ->
        WebEngineProvider(
            instance<RemoteDataSourceType, WebConfig>(arg = key).getConnectionTimeout()
        )
    }

    bind<WebProvider>() with multiton {
            key: RemoteDataSourceType ->

        WebProvider(
            instance<RemoteDataSourceType, WebConfig>(arg = key).getEndpoint().toString(),
            instance<RemoteDataSourceType, WebConfig>(arg = key).getLogger(),
            instance<RemoteDataSourceType, WebConfig>(arg = key).getLogLevel(),
            instance<RemoteDataSourceType, WebConfig>(arg = key).getHtAccessCredential(),
            instance<RemoteDataSourceType, WebEngineProvider>(arg = key).getEngine()
        )


    }

}


