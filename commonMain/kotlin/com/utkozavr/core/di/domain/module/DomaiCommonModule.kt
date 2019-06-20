package com.utkozavr.core.di.domain.module


import com.utkozavr.core.domain.common.DataContainer
import com.utkozavr.core.domain.common.DataContainerObserver
import com.utkozavr.core.domain.common.config.AppConfig
import com.utkozavr.core.ui.common.EndlessScrollObserverInterface
import com.utkozavr.core.ui.common.endlessscroll.EndlessScrollLoader
import com.utkozavr.core.ui.common.endlessscroll.config.EndlessScrollConfig
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.serialization.UnstableDefault
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.factory
import org.kodein.di.erased.singleton

@ExperimentalCoroutinesApi
@UnstableDefault
val domainCommonModule  = Kodein.Module("domainCommonModule"){

    bind<AppConfig>() with singleton { AppConfig() }

    bind<DataContainer>() with factory { observer: DataContainerObserver -> DataContainer(observer) }

}