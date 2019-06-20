package com.utkozavr.core.domain.common.config

import com.utkozavr.core.di.appKodein
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.serialization.UnstableDefault
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.erased.instance

class AppConfig {

    var appStage: AppStage? = null

    companion object: KodeinAware {
        @UnstableDefault
        @ExperimentalCoroutinesApi
        override val kodein: Kodein
            get() = appKodein

        fun getInstance(): AppConfig {
            val c by  instance<AppConfig>()
            return c
        }
    }

}