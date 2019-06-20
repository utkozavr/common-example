package com.utkozavr.core.di

import com.utkozavr.core.di.domain.domainKodein
import com.utkozavr.core.di.data.dataKodein
import com.utkozavr.core.di.ui.uiKodein
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.serialization.UnstableDefault
import org.kodein.di.Kodein


@ExperimentalCoroutinesApi
@UnstableDefault
val appKodein = Kodein.lazy {

    extend(dataKodein)
    extend(domainKodein)
    extend(uiKodein)
}