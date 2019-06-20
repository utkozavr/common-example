package com.utkozavr.core.di.ui

import com.utkozavr.core.di.ui.module.uiCommonModule
import com.utkozavr.core.di.ui.module.viewModelModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.serialization.UnstableDefault
import org.kodein.di.Kodein

@UnstableDefault
@ExperimentalCoroutinesApi
val uiKodein = Kodein.lazy{
    importOnce(uiCommonModule)
    importOnce(viewModelModule)
}