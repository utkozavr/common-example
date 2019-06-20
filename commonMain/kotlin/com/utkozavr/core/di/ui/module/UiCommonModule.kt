package com.utkozavr.core.di.ui.module


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
val uiCommonModule  = Kodein.Module("uiCommonModule"){

    bind<EndlessScrollLoader>() with factory {
            observer: EndlessScrollObserverInterface,
            config: EndlessScrollConfig ->

        EndlessScrollLoader(
            observer,
            config.pageCurrentIndex,
            config.pageLoadingIndex,
            config.countItemsOnPage,
            config.borderStart,
            config.borderEnd,
            config.previousDataCross,
            config.scrollDistanceFilter
        )
    }

}